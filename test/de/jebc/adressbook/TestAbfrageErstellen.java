package de.jebc.adressbook;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.InPin;
import de.jebc.adressbook.domain.Schluessel;

public class TestAbfrageErstellen {

    private String result;

    @Test
    public void ErstelltSelectStatement() {
        Schluessel key = new Schluessel(1);

        AbfrageErstellen sut = new AbfrageErstellen();
        sut.Result().wire(new InPin<Abfrage>() {

            @Override
            public void receive(Abfrage message) {
                result = message.getQuery();
            }
        });

        sut.Start().receive(key);

        assertEquals("SELECT * FROM Adressen WHERE id = 1", result);
    }
}