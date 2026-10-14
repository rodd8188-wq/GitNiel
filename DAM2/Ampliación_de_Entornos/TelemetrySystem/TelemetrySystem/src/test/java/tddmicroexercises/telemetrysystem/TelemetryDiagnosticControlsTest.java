package tddmicroexercises.telemetrysystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelemetryDiagnosticControlsTest
{
    private static final String CONNECTION_STRING = "*111#";
    private static final String DIAGNOSTIC_RESPONSE = "LAST TX rate................ 100 MBPS";

    @Mock
    private ITelemetryClient telemetryClient;

    private TelemetryDiagnosticControls controls;

    @BeforeEach
    void setUp()
    {
        controls = new TelemetryDiagnosticControls(telemetryClient);
    }

    @Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() throws Exception
    {
        when(telemetryClient.getOnlineStatus()).thenReturn(true);
        when(telemetryClient.receive()).thenReturn(DIAGNOSTIC_RESPONSE);

        controls.checkTransmission();

        verify(telemetryClient).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        verify(telemetryClient).receive();
        assertEquals(DIAGNOSTIC_RESPONSE, controls.getDiagnosticInfo());
    }

    @Test
    public void CheckTransmission_should_disconnect_before_trying_to_connect() throws Exception
    {
        when(telemetryClient.getOnlineStatus()).thenReturn(false, true);
        when(telemetryClient.receive()).thenReturn(DIAGNOSTIC_RESPONSE);

        controls.checkTransmission();

        InOrder order = inOrder(telemetryClient);
        order.verify(telemetryClient).disconnect();
        order.verify(telemetryClient).connect(CONNECTION_STRING);
    }

    @Test
    public void CheckTransmission_should_connect_send_and_receive_in_order() throws Exception
    {
        when(telemetryClient.getOnlineStatus()).thenReturn(false, true, true);
        when(telemetryClient.receive()).thenReturn(DIAGNOSTIC_RESPONSE);

        controls.checkTransmission();

        InOrder order = inOrder(telemetryClient);
        order.verify(telemetryClient).disconnect();
        order.verify(telemetryClient).connect(CONNECTION_STRING);
        order.verify(telemetryClient).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        order.verify(telemetryClient).receive();
    }

    @Test
    public void CheckTransmission_should_not_try_to_connect_again_when_already_online_after_disconnect() throws Exception
    {
        when(telemetryClient.getOnlineStatus()).thenReturn(true);
        when(telemetryClient.receive()).thenReturn(DIAGNOSTIC_RESPONSE);

        controls.checkTransmission();

        verify(telemetryClient, never()).connect(anyString());
    }

    @Test
    public void CheckTransmission_should_succeed_when_connection_works_on_the_last_retry() throws Exception
    {
        // 1st check (loop) false, 2nd false, 3rd false -> 3 connects; then online
        when(telemetryClient.getOnlineStatus()).thenReturn(false, false, false, true, true);
        when(telemetryClient.receive()).thenReturn(DIAGNOSTIC_RESPONSE);

        controls.checkTransmission();

        verify(telemetryClient, times(3)).connect(CONNECTION_STRING);
        verify(telemetryClient).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        assertEquals(DIAGNOSTIC_RESPONSE, controls.getDiagnosticInfo());
    }

    @Test
    public void CheckTransmission_should_throw_after_three_failed_connection_attempts() throws Exception
    {
        when(telemetryClient.getOnlineStatus()).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> controls.checkTransmission());

        assertEquals("Unable to connect.", exception.getMessage());
        verify(telemetryClient, times(3)).connect(CONNECTION_STRING);
    }

    @Test
    public void CheckTransmission_should_not_send_nor_receive_when_unable_to_connect() throws Exception
    {
        when(telemetryClient.getOnlineStatus()).thenReturn(false);

        assertThrows(Exception.class, () -> controls.checkTransmission());

        verify(telemetryClient, never()).send(anyString());
        verify(telemetryClient, never()).receive();
    }

    @Test
    public void CheckTransmission_should_leave_diagnostic_info_empty_when_unable_to_connect() throws Exception
    {
        controls.setDiagnosticInfo("stale info from a previous run");
        when(telemetryClient.getOnlineStatus()).thenReturn(false);

        assertThrows(Exception.class, () -> controls.checkTransmission());

        assertEquals("", controls.getDiagnosticInfo());
    }

    @Test
    public void CheckTransmission_should_replace_previous_diagnostic_info_with_the_new_response() throws Exception
    {
        controls.setDiagnosticInfo("old info");
        when(telemetryClient.getOnlineStatus()).thenReturn(true);
        when(telemetryClient.receive()).thenReturn("new info");

        controls.checkTransmission();

        assertEquals("new info", controls.getDiagnosticInfo());
    }

    @Test
    public void DiagnosticInfo_should_be_empty_before_any_transmission_check()
    {
        assertEquals("", controls.getDiagnosticInfo());
        verifyNoMoreInteractions(telemetryClient);
    }

    @Test
    public void DiagnosticInfo_should_be_settable()
    {
        controls.setDiagnosticInfo("some info");

        assertEquals("some info", controls.getDiagnosticInfo());
    }
}
