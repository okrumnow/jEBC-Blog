package de.jebc.adressbook.activities.loeschen;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.activities.adressdetails.AdressDetailsAnzeigen;
import de.jebc.adressbook.domain.Name;
import de.jebc.adressbook.domain.Schluessel;

public class TestMarkierteAdresseLoeschen extends StoringPinsTestclass<Void> {

    @Test
    public void testStandardablauf() throws Exception {

        Name name = new Name(new Schluessel(1), null, null);
        Connection conn = setupDatabase();

        MarkierteAdresseLoeschen sut = new MarkierteAdresseLoeschen(conn);
        storeResultPin(sut.Result());

        sut.Start().receive(name);

        assertTrue(resultCalled);
    }

    @Test
    public void testExceptionInQuery() throws Exception {
        Name name = new Name(new Schluessel(1), null, null);
        Connection conn = setupDatabaseWithErrorCausingQueryToFail();

        AdressDetailsAnzeigen sut = new AdressDetailsAnzeigen(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(name);

        assertNotNull(exception);
    }

    @Test
    public void testExceptionCausedByMissingDatabaseRow() throws Exception {
        Name name = new Name(new Schluessel(2), null, null);
        Connection conn = setupDatabase();

        AdressDetailsAnzeigen sut = new AdressDetailsAnzeigen(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(name);

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
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name', 'Vorname', 'Anschrift', 'Telefon', 'Privat')");
        return conn;
    }

}
