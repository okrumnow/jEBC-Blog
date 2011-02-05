package de.jebc.adressbook.data;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Schluessel;

public class TestEinfuegenKommandoAusfuehren extends StoringPinsTestclass<Schluessel> {

    @Test public void testAusfuehren() throws Exception {
        Connection conn = setupDatabase();
        Abfrage query = new Abfrage("INSERT INTO Adressen (Name) VALUES ('xyz')");

        EinfuegenKommandoAusfuehren sut = new EinfuegenKommandoAusfuehren(conn);
        storeResultPin(sut.Result());

        sut.Start().receive(query);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }
    
    @Test
    public void testFehlerhafterBefehlSendetExceptionAnExceptionPin()
            throws Exception {
        Connection conn = setupDatabase();
        Abfrage query = new Abfrage("INSERT INTO Unbekannt (Name) VALUES ('xyz')");

        EinfuegenKommandoAusfuehren sut = new EinfuegenKommandoAusfuehren(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(query);

        assertNotNull(exception);
    }

    private Connection setupDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT, Kategorie TEXT);");
        return conn;
    }


}
