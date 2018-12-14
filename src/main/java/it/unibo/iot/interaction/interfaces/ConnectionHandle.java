package it.unibo.iot.interaction.interfaces;

import java.io.IOException;

public interface ConnectionHandle {
    void send(String line) throws IOException;
    String receive() throws IOException;
}
