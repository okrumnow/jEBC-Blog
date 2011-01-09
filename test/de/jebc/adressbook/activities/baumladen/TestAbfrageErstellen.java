package de.jebc.adressbook.activities.baumladen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.activities.baumladen.AbfrageErstellen;
import de.jebc.adressbook.domain.Abfrage;

public class TestAbfrageErstellen extends StoringPinsTestclass<Abfrage> {

    @Test
    public void testErstellen() {

        AbfrageErstellen sut = new AbfrageErstellen();
        storeResultPin(sut.Result());
        
        sut.Start().receive(null);
        
        assertNotNull(result);
        assertEquals("SELECT * FROM Adressen", result.getQuery());

    }

}
