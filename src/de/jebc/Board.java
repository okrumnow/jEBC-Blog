package de.jebc;


public class Board {

    protected <T> void wire(OutPin<T> out, InPin<T> in, Watcher<T> watcher) {
        wire(out, watcher.In());
        wire(watcher.Out(), in);
    }

    protected <T> void wire(InPin<T> in, Watcher<T> watcher) {
        wire(watcher.Out(), in);
    }

    protected <T> void wire(OutPin<T> out, InPin<T> in) {
        out.wire(in);
    }

    protected <T> Watcher<T> watcher(Watcher<T> watcher) {
        return watcher;
    }

}