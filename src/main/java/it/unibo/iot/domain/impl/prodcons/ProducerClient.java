package it.unibo.iot.domain.impl.prodcons;

import it.unibo.iot.domain.interfaces.Emitter;
import it.unibo.iot.domain.interfaces.EmitterFactory;
import it.unibo.iot.domain.interfaces.Producer;
import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ProducerClient implements Producer, Runnable {
    private Emitter E;
    private Connection connection;
    private final String host;
    private final int port;
    private int k = 0;

    public ProducerClient(EmitterFactory ef, Connection connection, String host, int port) {
        this.E = ef.createEmitter(ProducerClient.class.getName());
        this.connection = connection;
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ConnectionHandle ch = connection.connectAsClient(host, port);
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
        E.emit("Produce " + k);
        return k;
    }

    @Override
    public boolean done() {
        return k > 3;
    }
}
