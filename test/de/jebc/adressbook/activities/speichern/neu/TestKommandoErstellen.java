package de.jebc.adressbook.activities.speichern.neu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;

public class TestKommandoErstellen extends StoringPinsTestclass<Abfrage> {

    @Test
    public void testErstellen() throws Exception {
        Adresse daten = new Adresse(new Schluessel(1), "Name", "Vorname",
                "Anschrift", "Telefon", "Kategorie");

        EinfuegenKommandoErstellen sut = new EinfuegenKommandoErstellen();
        storeResultPin(sut.Result());

        sut.Start().receive(daten);

        assertEquals(
                "INSERT INTO Adressen (Name, Vorname, Anschrift, Telefon, Kategorie) "
                        + "VALUES ('Name', 'Vorname', 'Anschrift', 'Telefon', 'Kategorie')",
                result.getQuery());

    }
}
