package de.jebc.adressbook.activities.loeschen;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;

public class TestErgebnisPruefen extends StoringPinsTestclass<Void> {

    @Test public void testErgebnis1Erwartet() throws Exception {
        
        Integer ergebnis = new Integer(1);
        ErgebnisPruefen sut = new ErgebnisPruefen();
        storeResultPin(sut.Result());
        
        sut.Start().receive(ergebnis);
        
        assertTrue(resultCalled);
    }

    @Test public void testErgebnis2WirftException() throws Exception {
        
        Integer ergebnis = new Integer(2);
        ErgebnisPruefen sut = new ErgebnisPruefen();
        storeExceptionPin(sut.Exception());
        
        sut.Start().receive(ergebnis);
        
        assertNotNull(exception);
    }
}
