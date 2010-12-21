package de.jebc.adressbook;

import java.sql.ResultSet;

import de.jebc.InPin;
import de.jebc.OutPin;

public class AdressobjektErstellen {

    private InPin<ResultSet> inpin;
    private OutPin<Adresse> outpin;

    public InPin<ResultSet> Start() {
        return inpin;
    }
    
    public OutPin<Adresse> Result() {
        return outpin;
    }
}
