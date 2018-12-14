package it.unibo.iot.interaction.impl;

import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public abstract class ZMQConnection implements Connection {
    private ZMQ.Socket s;

    abstract protected int getClientSocketType();
    abstract protected int getServerSocketType();

    @Override
    public ConnectionHandle connectAsClient(String host, int port) throws IOException {
        ZContext ctx = new ZContext();
        s = ctx.createSocket(getClientSocketType());
        s.connect("tcp://"+host+":"+port);
        return new ZMQConnectionHandle(s);
    }

    @Override
    public ConnectionHandle connectAsServer(int port) throws IOException {
        ZContext ctx = new ZContext();
        s = ctx.createSocket(getServerSocketType());
        s.bind("tcp://*:"+port);
        return new ZMQConnectionHandle(s);
    }

    @Override
    public void closeConnection() throws IOException {
        s.close();
    }
}
