package it.unibo.iot.interaction.impl;

import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.zeromq.ZMQ;

import java.io.IOException;

public class ZMQConnectionHandle implements ConnectionHandle {
    ZMQ.Socket s;

    public ZMQConnectionHandle(ZMQ.Socket s) {
        this.s = s;
    }

    @Override
    public void send(String line) throws IOException {
        s.send(line);
    }

    @Override
    public String receive() throws IOException {
        return s.recvStr();
    }
}
