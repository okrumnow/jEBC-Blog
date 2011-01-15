package de.jebc.adressbook.activities.loeschen;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.activities.loeschen.KommandoErstellen;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Schluessel;

public class TestKommandoErstellen extends StoringPinsTestclass<Abfrage> {

    @Test public void testErstellen() {
        
        KommandoErstellen sut = new KommandoErstellen();
        storeResultPin(sut.Result());
        
        sut.Start().receive(new Schluessel(1));
        assertNotNull(result);
        assertEquals("DELETE FROM Adressen WHERE id = 1", result.getQuery());
    }
}
