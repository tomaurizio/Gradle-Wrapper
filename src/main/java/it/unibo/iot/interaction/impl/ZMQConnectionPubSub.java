package it.unibo.iot.interaction.impl;

import org.zeromq.ZMQ;

public class ZMQConnectionPubSub extends ZMQConnection {

    protected int getClientSocketType(){ return ZMQ.PUB; };
    protected int getServerSocketType(){ return ZMQ.SUB; };

    @Override
    protected void configureServerSocketType(ZMQ.Socket s) {
        super.configureServerSocketType(s);
        s.subscribe("");
    }
}
