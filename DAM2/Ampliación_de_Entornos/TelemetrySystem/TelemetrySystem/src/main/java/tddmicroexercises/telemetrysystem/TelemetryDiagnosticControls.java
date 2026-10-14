package tddmicroexercises.telemetrysystem;

public class TelemetryDiagnosticControls
{
    private static final String DIAGNOSTIC_CHANNEL_CONNECTION_STRING = "*111#";
    private static final int MAX_CONNECTION_ATTEMPTS = 3;

    private final ITelemetryClient telemetryClient;
    private String diagnosticInfo = "";

    public TelemetryDiagnosticControls()
    {
        this(new TelemetryClient());
    }

    public TelemetryDiagnosticControls(ITelemetryClient telemetryClient)
    {
        this.telemetryClient = telemetryClient;
    }

    public String getDiagnosticInfo()
    {
        return diagnosticInfo;
    }

    public void setDiagnosticInfo(String diagnosticInfo)
    {
        this.diagnosticInfo = diagnosticInfo;
    }

    public void checkTransmission() throws Exception
    {
        diagnosticInfo = "";

        telemetryClient.disconnect();

        int retryLeft = MAX_CONNECTION_ATTEMPTS;
        while (!telemetryClient.getOnlineStatus() && retryLeft > 0)
        {
            telemetryClient.connect(DIAGNOSTIC_CHANNEL_CONNECTION_STRING);
            retryLeft -= 1;
        }

        if (!telemetryClient.getOnlineStatus())
        {
            throw new Exception("Unable to connect.");
        }

        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        diagnosticInfo = telemetryClient.receive();
    }
}
