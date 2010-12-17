package de.jebc.addressbook;

import java.sql.ResultSet;

import de.jebc.InPin;
import de.jebc.OutPin;

public class CreateAddress {

    private InPin<ResultSet> inpin;
    private OutPin<Address> outpin;

    public InPin<ResultSet> Start() {
        return inpin;
    }
    
    public OutPin<Address> Result() {
        return outpin;
    }
}
