package de.jebc.addressbook;

import java.sql.ResultSet;

import de.jebc.InPin;
import de.jebc.OutPin;

public class ExecuteDatabaseQuery {

    private InPin<Query> inpin;
    private OutPin<ResultSet> outpin;

    public InPin<Query> Start() {
        return inpin;
    }
    
    public OutPin<ResultSet> Result() {
        return outpin;
    }
}
