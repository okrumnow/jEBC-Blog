package de.jebc.log;

import org.slf4j.Logger;

public abstract class LogError<T> extends Log<T> {

    public LogError(Logger log) {
        super(log);
    }

    @Override
    protected void log(String message) {
        log.error(message);
    }

    @Override
    protected boolean isEnabled() {
        return log.isErrorEnabled();
    }

}
