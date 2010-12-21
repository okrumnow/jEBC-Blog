package de.jebc.adressbook.log;

import org.slf4j.Logger;

import de.jebc.adressbook.Abfrage;
import de.jebc.log.LogInfo;

public class LogAbfrage extends LogInfo<Abfrage> {

    public LogAbfrage(Logger log) {
        super(log);
    }

    @Override
    protected String getMessage(Abfrage message) {
        return "Abfrage ist: " + message.getQuery();
    }

}
