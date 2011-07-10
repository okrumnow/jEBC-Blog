package de.jebc;

public interface InPin<T> {
    void receive(T message) throws Exception;
}
