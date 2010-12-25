package de.jebc.adressbook;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.Watcher;

public class Board {

    public Board() {
        super();
    }

    protected <T> void watch(OutPin<T> result, InPin<T> start, Watcher<T> with) {
        wire(result, with.In());
        wire(with.Out(), start);
    }

    protected <T> void watch(InPin<T> start, Watcher<T> watcher) {
        wire(watcher.Out(), start);
    }

    protected <T> void wire(OutPin<T> out, InPin<T> in) {
        out.wire(in);
    }

    protected <T> Watcher<T> with(Watcher<T> watcher) {
        return watcher;
    }

}