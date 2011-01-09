package de.jebc.adressbook.data;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Abfrage;

public class TestDatenbankabfrageAusfuehren extends
        StoringPinsTestclass<ResultSet> {

    @Test
    public void testAusfuehren() throws Exception {
        Connection conn = setupDatabase();
        Abfrage query = new Abfrage("SELECT * FROM Adressen");

        DatenbankabfrageAusfuehren sut = new DatenbankabfrageAusfuehren(conn);
        storeResultPin(sut.Result());

        sut.Start().receive(query);

        assertNotNull(result);
        assertTrue(result.next());
    }

    @Test
    public void testFehlerhafterBefehlSendetExceptionAnExceptionPin()
            throws Exception {
        Connection conn = setupDatabase();
        Abfrage query = new Abfrage("SELECT * FROM Unbekannt");

        DatenbankabfrageAusfuehren sut = new DatenbankabfrageAusfuehren(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(query);

        assertNotNull(exception);
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
