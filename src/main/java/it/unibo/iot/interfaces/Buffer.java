package it.unibo.iot.interfaces;

public interface Buffer<T> {
    boolean isEmpty();
    void add(T element);
    T next();
}
