package de.jebc.adressbook.log;

import org.slf4j.Logger;

import de.jebc.Watcher;
import de.jebc.adressbook.Schluessel;

public class LogSchluessel extends Watcher<Schluessel> {

    private final Logger log;

    public LogSchluessel(Logger log) {
        this.log = log;
    }

    protected void inspect(Schluessel message) {
        log.info("erstelle Abfrage für {}", message.getId());
    }
}
