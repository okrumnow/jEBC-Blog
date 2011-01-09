package de.jebc.adressbook.activities.adressdetails;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Name;
import de.jebc.adressbook.domain.Schluessel;

public class TestAdressDetailsAnzeigen extends StoringPinsTestclass<Adresse> {

    @Test
    public void testStandardablauf() throws Exception {

        Name name = new Name(new Schluessel(1), null, null);
        Connection conn = setupDatabase();

        AdressDetailsAnzeigen sut = new AdressDetailsAnzeigen(conn);
        storeResultPin(sut.Result());
        
        sut.Start().receive(name);

        assertNotNull(result);
        assertEquals(new Schluessel(1), result.getKey());
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
