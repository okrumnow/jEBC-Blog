package de.jebc.log;

import org.slf4j.Logger;

public abstract class LogWarn<T> extends Log<T> {

    public LogWarn(Logger log) {
        super(log);
    }

    @Override
    protected void log(String message) {
        log.warn(message);
    }

    @Override
    protected boolean isEnabled() {
        return log.isWarnEnabled();
    }

}
