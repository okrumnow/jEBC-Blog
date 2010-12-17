package de.jebc.adressbook;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.InPin;
import de.jebc.addressbook.CreateQueryFromKey;
import de.jebc.addressbook.Key;
import de.jebc.addressbook.Query;

public class TestCreateQuery {

    private String result;

    @Test
    public void ErstelltSelectStatement() {
        Key key = new Key(1);

        CreateQueryFromKey sut = new CreateQueryFromKey();
        sut.Result().wire(new InPin<Query>() {

            @Override
            public void receive(Query message) {
                result = message.getQuery();
            }
        });

        sut.Start().receive(key);

        assertEquals("SELECT * FROM Adressen WHERE id = 1", result);
    }
}