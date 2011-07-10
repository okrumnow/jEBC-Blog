package de.jebc.adressbook.activities.adressdetails;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.activities.adressdetails.AbfrageErstellen;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Schluessel;

public class TestAbfrageErstellen extends StoringPinsTestclass<Abfrage> {

    @Test
    public void ErstelltSelectStatement() throws Exception {
        Schluessel key = new Schluessel(1);

        AbfrageErstellen sut = new AbfrageErstellen();
        storeResultPin(sut.Result());

        sut.Start().receive(key);

        assertEquals("SELECT * FROM Adressen WHERE id = 1", result.getQuery());
    }
}