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
    
    protected <TIn1, TIn2, TOut> OutPin<TOut> join(OutPin<TIn1> out1, OutPin<TIn2> out2, Join<TIn1, TIn2, TOut> joiner) {
        wire(out1, joiner.In1());
        wire(out2, joiner.In2());
        return joiner.Out();
    }

}