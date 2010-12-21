package de.jebc.adressbook;

import java.sql.ResultSet;

import de.jebc.InPin;
import de.jebc.OutPin;

public class DatenbankabfrageAusfuehren {

    private InPin<Abfrage> inpin;
    private OutPin<ResultSet> outpin;

    public InPin<Abfrage> Start() {
        return inpin;
    }
    
    public OutPin<ResultSet> Result() {
        return outpin;
    }
}
