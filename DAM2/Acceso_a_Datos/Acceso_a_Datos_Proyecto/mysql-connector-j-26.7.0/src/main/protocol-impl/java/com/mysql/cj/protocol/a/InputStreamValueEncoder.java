/*
 * Copyright (c) 2022, 2026, Oracle and/or its affiliates.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License, version 2.0, as published by
 * the Free Software Foundation.
 *
 * This program is designed to work with certain software that is licensed under separate terms, as designated in a particular file or component or in
 * included license documentation. The authors of MySQL hereby grant you an additional permission to link the program and your derivative works with the
 * separately licensed software that they have either included with the program or referenced in the documentation.
 *
 * Without limiting anything contained in the foregoing, this file, which is part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0, for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 */

package com.mysql.cj.protocol.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.mysql.cj.BindValue;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;
import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.Util;

public class InputStreamValueEncoder extends AbstractValueEncoder {

    private byte[] streamConvertBuf = null;

    @Override
    public byte[] getBytes(BindValue binding) {
        return streamToBytes((InputStream) binding.getValue(), binding.getScaleOrLength(), null);
    }

    @Override
    public String getString(BindValue binding) {
        return "'** STREAM DATA **'";
    }

    @Override
    public void encodeAsText(Message msg, BindValue binding) {
        NativePacketPayload intoPacket = (NativePacketPayload) msg;
        streamToBytes((InputStream) binding.getValue(), binding.getScaleOrLength(), intoPacket);
    }

    @Override
    public void encodeAsBinary(Message msg, BindValue binding) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    protected byte[] streamToBytes(InputStream in, long length, NativePacketPayload packet) {
        boolean useLength = length == -1 ? false : this.propertySet.getBooleanProperty(PropertyKey.useStreamLengthsInPrepStmts).getValue();
        in.mark(Integer.MAX_VALUE); // This same stream may have to be read several times, so it must be reset at the end.
        try {
            if (this.streamConvertBuf == null) {
                this.streamConvertBuf = new byte[4096];
            }
            int bcnt = useLength ? Util.readBlock(in, this.streamConvertBuf, length, this.exceptionInterceptor)
                    : Util.readBlock(in, this.streamConvertBuf, this.exceptionInterceptor);
            long lengthLeftToRead = length - bcnt;

            ByteArrayOutputStream bytesOut = null;
            if (packet == null) {
                bytesOut = new ByteArrayOutputStream();
            } else {
                packet.writeBytes(StringLengthDataType.STRING_FIXED, StringUtils.getBytes("X"));
                packet.writeInteger(IntegerDataType.INT1, (byte) '\'');
            }

            while (bcnt > 0) {
                if (packet == null) {
                    bytesOut.write(this.streamConvertBuf, 0, bcnt);
                } else {
                    StringUtils.hexEscapeBlock(this.streamConvertBuf, bcnt, (lowBits, highBits) -> {
                        packet.writeInteger(IntegerDataType.INT1, lowBits);
                        packet.writeInteger(IntegerDataType.INT1, highBits);
                    });
                }

                if (useLength) {
                    bcnt = Util.readBlock(in, this.streamConvertBuf, lengthLeftToRead, this.exceptionInterceptor);
                    if (bcnt > 0) {
                        lengthLeftToRead -= bcnt;
                    }
                } else {
                    bcnt = Util.readBlock(in, this.streamConvertBuf, this.exceptionInterceptor);
                }
            }

            if (packet == null) {
                return bytesOut.toByteArray();
            }

            packet.writeInteger(IntegerDataType.INT1, (byte) '\'');
            return null;

        } finally {
            try {
                in.reset();
            } catch (IOException e) {
            }
            if (this.propertySet.getBooleanProperty(PropertyKey.autoClosePStmtStreams).getValue()) {
                try {
                    in.close();
                } catch (IOException ioEx) {
                }

                in = null;
            }
        }
    }

}
