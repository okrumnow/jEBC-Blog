package de.jebc.adressbook.log;

import org.slf4j.Logger;

public abstract class LogInfo<T> extends Log<T> {

    public LogInfo(Logger log) {
        super(log);
    }

    @Override
    protected void log(String message) {
        log.info(message);
    }

    @Override
    protected boolean enabled() {
        return log.isInfoEnabled();
    }

}
