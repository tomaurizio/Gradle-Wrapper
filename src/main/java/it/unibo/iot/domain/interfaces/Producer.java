package it.unibo.iot.domain.interfaces;

public interface Producer {
    Object produce();

    boolean done();
}
