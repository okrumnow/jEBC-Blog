package de.jebc.examples;

import de.jebc.InPin;
import de.jebc.OutPin;

public class Funktionseinheit {

    private OutPin<String> outPin;

    public Funktionseinheit() {
        outPin.wire(new InPin<String>() {

            @Override
            public void receive(String message) {
                // mach was
            }
        });
    }
}
