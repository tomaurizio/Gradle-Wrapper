package it.unibo.iot.domain.interfaces;

public interface Buffer<T> {
    boolean isEmpty();
    void add(T element);
    T next();
}
