package de.jebc.adressbook.activities.speichern.neu;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;

public class TestAdresseAnlegen extends StoringPinsTestclass<Adresse> {

    @Test
    public void testAnlegen() throws Exception {
        Adresse daten = new Adresse(new Schluessel(0), "Name", "Vorname",
                "Anschrift", "Telefon", "Kategorie");
        Connection conn = setupDatabase();

        AdresseAnlegen sut = new AdresseAnlegen(conn);
        storeResultPin(sut.Result());

        sut.Start().receive(daten);

        assertTrue(resultCalled);
        assertNotSame(daten.getKey(), result.getKey());
        assertFalse(daten.getKey().equals(result.getKey()));
    }

    @Test
    public void testCommandFails() throws Exception {
        Adresse daten = new Adresse(new Schluessel(0), "Name", "Vorname",
                "Anschrift", "Telefon", "Kategorie");
        Connection conn = setupDatabaseWithErrorCausingCommandToFail();

        AdresseAnlegen sut = new AdresseAnlegen(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(daten);

        assertNotNull(exception);
        assertNull(result);
    }

    private Connection setupDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT, Kategorie TEXT);");
        return conn;
    }

    private Connection setupDatabaseWithErrorCausingCommandToFail()
            throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE WrongName (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT, Kategorie TEXT);");
        return conn;
    }

}
