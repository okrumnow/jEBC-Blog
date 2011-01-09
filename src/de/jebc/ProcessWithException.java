package de.jebc;

public abstract class ProcessWithException<TInput, TOutput> extends
        Process<TInput, TOutput> {

    private OutPin<Exception> exceptionPin = new OutPinImpl<Exception>();

    public OutPin<Exception> Exception() {
        return exceptionPin;
    }
}
