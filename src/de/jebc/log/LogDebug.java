package de.jebc.log;

import org.slf4j.Logger;

public abstract class LogDebug<T> extends Log<T> {

    public LogDebug(Logger log) {
        super(log);
    }

    @Override
    protected void log(String message) {
        log.debug(message);
    }

    @Override
    protected boolean enabled() {
        return log.isDebugEnabled();
    }

}
