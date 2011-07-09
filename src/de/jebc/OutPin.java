package de.jebc;

public interface OutPin<T> {
    void wire(InPin<T> in);
    void send (T message) throws Exception;
}
