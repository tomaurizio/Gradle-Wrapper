package it.unibo.iot.impl;

import it.unibo.iot.interfaces.Buffer;
import it.unibo.iot.interfaces.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerActivity implements Consumer, Runnable {
    private static final Logger L = LoggerFactory.getLogger(ProducerActivity.class);
    private Buffer queue;

    public ConsumerActivity(Buffer queue) {
        this.queue = queue;
    }

    @Override
    public void consume(Object element) {
        L.info("Consume " + element);
    }

    @Override
    public void run() {
        while(true){
            consume(queue.next());
        }
    }
}
