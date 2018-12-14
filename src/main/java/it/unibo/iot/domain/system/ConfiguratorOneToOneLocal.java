package it.unibo.iot.domain.system;

import it.unibo.iot.domain.impl.ConcurrentQueueBuffer;
import it.unibo.iot.domain.impl.ConsumerActivity;
import it.unibo.iot.domain.impl.ProducerActivity;
import it.unibo.iot.domain.interfaces.Buffer;
import it.unibo.iot.domain.interfaces.Configurator;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ConfiguratorOneToOneLocal implements Configurator {
    private Runnable producer;
    private Runnable consumer;
    private Buffer buffer;
    private int bufferCapacity;

    public static void main(String[] args) throws InterruptedException {
        ConfiguratorOneToOneLocal configurator = new ConfiguratorOneToOneLocal(10);
        configurator.setup();
        configurator.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        configurator.teardown();
    }

    public ConfiguratorOneToOneLocal(int bufferCapacity) {
        this.bufferCapacity = bufferCapacity;
    }

    @Override public void setup(){
        buffer = new ConcurrentQueueBuffer(bufferCapacity);
        producer = new ProducerActivity(buffer);
        consumer = new ConsumerActivity(buffer);
    }

    @Override public void start(){
        ForkJoinPool.commonPool().execute(consumer);
        ForkJoinPool.commonPool().execute(producer);
    }

    @Override public void teardown(){
        System.exit(0);
    }
}
