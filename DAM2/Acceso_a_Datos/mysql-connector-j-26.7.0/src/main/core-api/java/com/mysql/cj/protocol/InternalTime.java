/*
 * Copyright (c) 2019, 2026, Oracle and/or its affiliates.
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

package com.mysql.cj.protocol;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.Calendar;

import com.mysql.cj.util.TimeUtil;

public class InternalTime {

    private boolean negative = false;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private int nanos = 0;
    private int scale = 0;

    public static InternalTime from(LocalTime x) {
        return new InternalTime(x.getHour(), x.getMinute(), x.getSecond(), x.getNano(), -1);
    }

    public static InternalTime from(LocalDateTime x) {
        return new InternalTime(x.getHour(), x.getMinute(), x.getSecond(), x.getNano(), -1);
    }

    public static InternalTime from(OffsetTime x) {
        return new InternalTime(x.getHour(), x.getMinute(), x.getSecond(), x.getNano(), -1);
    }

    public static InternalTime from(Duration x) {
        Duration durationAbs = x.abs();
        long durationSeconds = durationAbs.getSeconds();
        int hours = (int) (durationSeconds / 3600);
        int minutes = (int) (durationSeconds % 3600 / 60);
        int seconds = (int) (durationSeconds % 60);
        int nanos = durationAbs.getNano();
        InternalTime internalTime = new InternalTime(hours, minutes, seconds, nanos, -1);
        internalTime.setNegative(x.isNegative());
        return internalTime;
    }

    public static InternalTime from(Calendar x, int nanos) {
        return new InternalTime(x.get(Calendar.HOUR_OF_DAY), x.get(Calendar.MINUTE), x.get(Calendar.SECOND), nanos, -1);
    }

    /**
     * Constructs a zero time
     */
    public InternalTime() {
    }

    public InternalTime(int hours, int minutes, int seconds, int nanos, int scale) {
        setHours(hours);
        this.minutes = minutes;
        this.seconds = seconds;
        this.nanos = nanos;
        this.scale = scale;
    }

    public boolean isNegative() {
        return this.negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = Math.abs(hours);
        if (hours < 0) {
            setNegative(true);
        }
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getNanos() {
        return this.nanos;
    }

    public void setNanos(int nanos) {
        this.nanos = nanos;
    }

    public boolean isZero() {
        return this.hours == 0 && this.minutes == 0 && this.seconds == 0 && this.nanos == 0;
    }

    public int getScale() {
        return this.scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        if (this.nanos > 0) {
            return String.format("%s%02d:%02d:%02d.%s", this.negative ? "-" : "", this.hours, this.minutes, this.seconds,
                    TimeUtil.formatNanos(this.nanos, this.scale, false));
        }
        return String.format("%s%02d:%02d:%02d", this.negative ? "-" : "", this.hours, this.minutes, this.seconds);
    }

}
