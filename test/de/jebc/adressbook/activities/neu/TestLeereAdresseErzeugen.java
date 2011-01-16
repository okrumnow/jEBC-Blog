package de.jebc.adressbook.activities.neu;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Adresse;

public class TestLeereAdresseErzeugen extends StoringPinsTestclass<Adresse> {

    @Test
    public void testErzeugen() {

        LeereAdresseErzeugen sut = new LeereAdresseErzeugen();
        storeResultPin(sut.Result());

        sut.Start().receive(null);

        assertNotNull(result);
        assertEquals(0, result.getKey().getId());
    }
}
