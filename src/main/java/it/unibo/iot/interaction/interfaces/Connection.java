package it.unibo.iot.interaction.interfaces;

import java.io.IOException;

public interface Connection {
    ConnectionHandle connectAsClient(String host, int port) throws IOException;
    ConnectionHandle connectAsServer(int port) throws IOException;
    void closeConnection() throws IOException;
}
