package de.jebc;

public class BoardWithException extends Board {

    private Extender<Exception> extender = new Extender<Exception>();

    protected void handle(OutPin<Exception> exception) {
        wire(exception, extender.In());
    }

    public OutPin<Exception> Exception() {
        return extender.Out();
    }
}
