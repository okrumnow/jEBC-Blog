package de.jebc.adressbook.activities.speichern.update;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;

public class TestAdresseAktualisieren extends StoringPinsTestclass<Adresse> {

    @Test
    public void testStandardablauf() throws Exception {

        Adresse daten = new Adresse(new Schluessel(1), "Name", "Vorname",
                "Anschrift", "Telefon", "Kategorie");
        Connection conn = setupDatabase();

        AdresseAktualisieren sut = new AdresseAktualisieren(conn);
        storeResultPin(sut.Result());

        sut.Start().receive(daten);

        assertTrue(resultCalled);
    }

    @Test
    public void testExceptionInQuery() throws Exception {
        Adresse daten = new Adresse(new Schluessel(1), "Name", "Vorname",
                "Anschrift", "Telefon", "Kategorie");
        Connection conn = setupDatabaseWithErrorCausingQueryToFail();

        AdresseAktualisieren sut = new AdresseAktualisieren(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(daten);

        assertNotNull(exception);
    }

    @Test
    public void testExceptionCausedByMissingDatabaseRow() throws Exception {
        Adresse daten = new Adresse(new Schluessel(2), "Name", "Vorname",
                "Anschrift", "Telefon", "Kategorie");
        Connection conn = setupDatabase();

        AdresseAktualisieren sut = new AdresseAktualisieren(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(daten);

        assertNotNull(exception);
    }

    private Connection setupDatabaseWithErrorCausingQueryToFail()
            throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE WrongTableName (ID INTEGER, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT, Kategorie TEXT);");
        stmt.executeUpdate("INSERT INTO WrongTableName VALUES (1, 'Name', 'Vorname', 'Anschrift', 'Telefon', 'Privat')");
        return conn;
    }

    private Connection setupDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT, Kategorie TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, '', '', '', '', '')");
        return conn;
    }

}
