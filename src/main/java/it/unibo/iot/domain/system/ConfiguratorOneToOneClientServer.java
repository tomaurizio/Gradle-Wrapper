package it.unibo.iot.domain.system;

import it.unibo.iot.domain.impl.*;
import it.unibo.iot.domain.interfaces.Configurator;
import it.unibo.iot.interaction.impl.ZMQConnectionFactory;
import it.unibo.iot.interaction.interfaces.ConnectionFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ConfiguratorOneToOneClientServer implements Configurator {
    private Runnable producerClient;
    private Runnable consumerServer;
    private int bufferCapacity;
    ConnectionFactory connectionFactory;
    private static final int port = 8001;

    public static void main(String[] args) throws InterruptedException {
        //ConnectionFactory factory = new TCPConnectionFactory();
        ConnectionFactory factory = new ZMQConnectionFactory();
        ConfiguratorOneToOneClientServer configurator = new ConfiguratorOneToOneClientServer(10, factory);
        configurator.setup();
        configurator.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        configurator.teardown();
    }

    public ConfiguratorOneToOneClientServer(int bufferCapacity, ConnectionFactory cf) {
        this.bufferCapacity = bufferCapacity;
        this.connectionFactory = cf;
    }

    @Override public void setup(){
        consumerServer = new ConsumerServer(connectionFactory.connection(), port);
        producerClient = new ProducerClient(connectionFactory.connection(), port);
    }

    @Override public void start(){
        ForkJoinPool.commonPool().execute(consumerServer);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ForkJoinPool.commonPool().execute(producerClient);
    }

    @Override public void teardown(){
        System.exit(0);
    }
}
