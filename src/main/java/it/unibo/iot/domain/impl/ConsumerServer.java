package it.unibo.iot.domain.impl;

import it.unibo.iot.domain.interfaces.Consumer;
import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ConsumerServer implements Consumer, Runnable {
    private static final Logger L = LoggerFactory.getLogger(ConsumerServer.class);
    private Connection connection;
    private int port;

    public ConsumerServer(Connection connection, int port) {
        this.connection = connection;
        this.port = port;
    }

    @Override
    public void consume(Object element) {
        L.info("Consume " + element);
    }

    @Override
    public void run() {
        try {
            ConnectionHandle ch = connection.connectAsServer(this.port);
            while(true){
                String element = ch.receive();
                consume(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
