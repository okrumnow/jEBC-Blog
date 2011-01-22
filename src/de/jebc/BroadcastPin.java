package de.jebc;

import java.util.ArrayList;
import java.util.List;

public class BroadcastPin<T> implements OutPin<T> {

    private List<InPin<T>> inPins = new ArrayList<InPin<T>>();

    @Override
    public void wire(InPin<T> in) {
        if (!inPins.contains(in))
            inPins.add(in);
    }

    @Override
    public void send(T message) {
        for (InPin<T> pin : inPins) {
            pin.receive(message);
        }
    }

}
