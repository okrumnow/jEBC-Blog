package de.jebc.adressbook.log;

import org.slf4j.Logger;

import de.jebc.adressbook.Abfrage;

public class LogAbfrage extends Watcher<Abfrage> {

    private final Logger log;

    public LogAbfrage(Logger log) {
        this.log = log;
    }

    protected void inspect(Abfrage message) {
        log.info("Abfrage ist: {}", message.getQuery());
    }
}
