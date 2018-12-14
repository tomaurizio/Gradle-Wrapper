package it.unibo.iot.interaction.impl;

import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

/**
 * Note: assumes the client does always "send" and server does always "receive".
 */
public class ZMQConnection implements Connection {
    private ZMQ.Socket s;

    @Override
    public ConnectionHandle connectAsClient(String host, int port) throws IOException {
        ZContext ctx = new ZContext();
        s = ctx.createSocket(ZMQ.PUB);
        s.connect("tcp://"+host+":"+port);
        return new ZMQConnectionHandle(s);
    }

    @Override
    public ConnectionHandle connectAsServer(int port) throws IOException {
        ZContext ctx = new ZContext();
        s = ctx.createSocket(ZMQ.SUB);
        s.subscribe("");
        s.bind("tcp://*:"+port);
        return new ZMQConnectionHandle(s);
    }

    @Override
    public void closeConnection() throws IOException {
        s.close();
    }
}
