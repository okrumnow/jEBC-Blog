package de.jebc;

public class OutPinImpl<T> implements OutPin<T> {

    private InPin<T> inPin;

    @Override
    public void wire(InPin<T> pin) {
        inPin = pin;
    }

    @Override
    public void send(T message) throws Exception {
        inPin.receive(message);
    }

}
