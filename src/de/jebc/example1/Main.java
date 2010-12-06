package de.jebc.example1;

import de.jebc.InPin;
import de.jebc.OutPin;

public class Main {

    private InPin<String> in;
    private OutPin<String> out;
    
    private void connect(OutPin<String> outPin, InPin<String> inPin) {
        outPin.wire(inPin);
    }
}
