package it.unibo.iot.domain.impl.prodcons;

import it.unibo.iot.domain.interfaces.Buffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConcurrentQueueBuffer implements Buffer {
    private BlockingQueue queue;

    public ConcurrentQueueBuffer(int bufferCapacity){
        this.queue = new ArrayBlockingQueue(bufferCapacity);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void add(Object element) {
        queue.add(element);
    }

    @Override
    public Object next() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
