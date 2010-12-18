package de.jebc.log;

import org.slf4j.Logger;

public abstract class LogTrace<T> extends Log<T> {

    public LogTrace(Logger log) {
        super(log);
    }

    @Override
    protected void log(String message) {
        log.trace(message);
    }

    @Override
    protected boolean isEnabled() {
        return log.isTraceEnabled();
    }

}
