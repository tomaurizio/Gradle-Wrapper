package it.unibo.iot.interfaces;

public interface Configurator {
    default void setup() { }
    default void start() { }
    default void teardown() { }
}
