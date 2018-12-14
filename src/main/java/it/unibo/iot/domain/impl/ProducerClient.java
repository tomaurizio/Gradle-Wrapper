package it.unibo.iot.domain.impl;

import it.unibo.iot.domain.interfaces.Producer;
import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ProducerClient implements Producer, Runnable {
    private static final Logger L = LoggerFactory.getLogger(ProducerClient.class);
    private Connection connection;
    private int port;
    private int k = 0;

    public ProducerClient(Connection connection, int port) {
        this.connection = connection;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ConnectionHandle ch = connection.connectAsClient("127.0.0.1", port);
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(1000));
            while (!done()) {
                ch.send(produce().toString());
                Thread.sleep(TimeUnit.MILLISECONDS.toMillis(500));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object produce() {
        k++;
        L.info("Produce " + k);
        return k;
    }

    @Override
    public boolean done() {
        return k > 3;
    }
}
