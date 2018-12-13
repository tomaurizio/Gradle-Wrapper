package it.unibo.iot.system;

import it.unibo.iot.impl.ConcurrentQueueBuffer;
import it.unibo.iot.impl.ConsumerActivity;
import it.unibo.iot.impl.ProducerActivity;
import it.unibo.iot.interfaces.Buffer;
import it.unibo.iot.interfaces.Configurator;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ConfiguratorOneToOne implements Configurator {
    private Runnable producer;
    private Runnable consumer;
    private Buffer buffer;
    private int bufferCapacity;

    public static void main(String[] args) throws InterruptedException {
        ConfiguratorOneToOne configurator = new ConfiguratorOneToOne(10);
        configurator.setup();
        configurator.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        configurator.teardown();
    }

    public ConfiguratorOneToOne(int bufferCapacity) {
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
