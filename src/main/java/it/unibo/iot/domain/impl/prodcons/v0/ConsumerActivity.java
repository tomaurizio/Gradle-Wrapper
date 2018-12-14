package it.unibo.iot.domain.impl.prodcons.v0;

import it.unibo.iot.domain.interfaces.Buffer;
import it.unibo.iot.domain.interfaces.Consumer;
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
