package de.jebc.adressbook.activities;

import de.jebc.InPin;
import de.jebc.OutPin;

public class StoringPinsTestclass<T> {

    protected T result;
    protected Exception exception;
    protected boolean resultCalled;
    protected boolean exceptionCalled;

    public StoringPinsTestclass() {
        super();
    }

    protected void storeResultPin(OutPin<T> pin) {
        pin.wire(new InPin<T>() {

            @Override
            public void receive(T message) {
                result = message;
                resultCalled = true;
            }
        });
    }

    protected void storeExceptionPin(OutPin<Exception> pin) {
        pin.wire(new InPin<Exception>() {
    
            @Override
            public void receive(Exception message) {
                exception = message;
                exceptionCalled = true;
            }
        });
    }

}