package tddmicroexercises.telemetrysystem;

public interface ITelemetryClient
{
    boolean getOnlineStatus();

    void connect(String telemetryServerConnectionString);

    void disconnect();

    void send(String message);

    String receive();
}
