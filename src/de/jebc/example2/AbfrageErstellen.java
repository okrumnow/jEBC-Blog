package de.jebc.example2;

import de.jebc.InPin;
import de.jebc.OutPin;

public class AbfrageErstellen {

    private InPin<Schluessel> inpin;
    private OutPin<Abfrage> outpin;

    public InPin<Schluessel> Start() {
        return inpin;
    }
    
    public OutPin<Abfrage> Result() {
        return outpin;
    }
}
