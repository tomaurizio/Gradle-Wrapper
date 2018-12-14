package it.unibo.iot.domain.interfaces;

public interface Configurator {
    default void setup() { }
    default void start() { }
    default void teardown() { }
}
