package de.jebc.adressbook.activities.speichern.update;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;

public class TestKommandoErstellen extends StoringPinsTestclass<Abfrage> {

    @Test
    public void testErstellen() {
        Adresse daten = new Adresse(new Schluessel(1), "Name", "Vorname",
                "Anschrift", "Telefon", "Kategorie");

        KommandoErstellen sut = new KommandoErstellen();
        storeResultPin(sut.Result());

        sut.Start().receive(daten);

        assertEquals(
                "UPDATE Adressen SET Name = 'Name', Vorname = 'Vorname', Anschrift = 'Anschrift', Telefon = 'Telefon', Kategorie = 'Kategorie' WHERE ID = 1",
                result.getQuery());
    }
}
