package it.unibo.iot.interaction.impl;

import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public class ZMQConnectionPair extends ZMQConnection {

    protected int getClientSocketType(){ return ZMQ.PAIR; };
    protected int getServerSocketType(){ return ZMQ.PAIR; };

}
