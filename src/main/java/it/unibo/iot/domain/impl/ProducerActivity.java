package it.unibo.iot.domain.impl;

import it.unibo.iot.domain.interfaces.Buffer;
import it.unibo.iot.domain.interfaces.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ProducerActivity implements Producer, Runnable {
    private static final Logger L = LoggerFactory.getLogger(ProducerActivity.class);
    private Buffer queue;
    private int k = 0;

    public ProducerActivity(Buffer queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(!done()) {
            queue.add(produce());
            try {
                Thread.sleep(TimeUnit.MILLISECONDS.toMillis(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
