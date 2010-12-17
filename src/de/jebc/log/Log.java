package de.jebc.log;

import org.slf4j.Logger;

import de.jebc.Watcher;

public abstract class Log<T> extends Watcher<T> {

    protected final Logger log;

    public Log(Logger log) {
        this.log = log;
    }

    @Override
    protected void inspect(T message) {
        if (enabled()) {
            log(getMessage(message));
        }
    }

    protected abstract void log(String message);

    protected abstract String getMessage(T message);

    protected abstract boolean enabled();

}
