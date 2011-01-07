package de.jebc.adressbook.activities.adressdetails;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.adressbook.activities.adressdetails.AdressobjektErstellen;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;

public class TestAdressobjektErstellen {

    protected Adresse result;
    protected Exception exception;

    @Test
    public void testAdresseEinlesen() throws Exception {
        ResultSet rs = getResultSet();

        AdressobjektErstellen sut = new AdressobjektErstellen();

        storeResultPin(sut.Result());

        sut.Start().receive(rs);

        verify(rs).next();
        assertEquals(new Schluessel(1), result.getKey());
        assertEquals("Name", result.getName());
        assertEquals("Vorname", result.getVorname());
        assertEquals("Anschrift", result.getAnschrift());
        assertEquals("Telefon", result.getTelefon());
        assertEquals("Kategorie", result.getKategorie());
    }

    @Test
    public void testAdresseEinlesenWirftException() throws Exception {
        ResultSet rs = getResultSetThrowingException();

        AdressobjektErstellen sut = new AdressobjektErstellen();

        storeExceptionPin(sut.Exception());

        sut.Start().receive(rs);

        assertNotNull(exception);
    }
    
    @Test
    public void testLeeresResultsetErzeugtExceptionAmExceptionPin() throws Exception {
        ResultSet rs = getEmptyResultSet();

        AdressobjektErstellen sut = new AdressobjektErstellen();

        storeExceptionPin(sut.Exception());

        sut.Start().receive(rs);

        assertNotNull(exception);
    }

    private ResultSet getResultSet() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.next()).thenReturn(true, false);
        when(result.getInt("ID")).thenReturn(1);
        when(result.getString("Name")).thenReturn("Name");
        when(result.getString("Vorname")).thenReturn("Vorname");
        when(result.getString("Anschrift")).thenReturn("Anschrift");
        when(result.getString("Telefon")).thenReturn("Telefon");
        when(result.getString("Kategorie")).thenReturn("Kategorie");
        //
        return result;
    }

    private ResultSet getResultSetThrowingException() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.next()).thenReturn(true);
        when(result.getInt("ID")).thenThrow(
                new SQLException("Something went terribly wrong"));
        return result;
    }

    private ResultSet getEmptyResultSet() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.next()).thenReturn(false);
        return result;
    }

    private void storeExceptionPin(OutPin<Exception> pin) {
        pin.wire(new InPin<Exception>() {
    
            @Override
            public void receive(Exception message) {
                exception = message;
            }
        });
    }

    private void storeResultPin(OutPin<Adresse> pin) {
        pin.wire(new InPin<Adresse>() {

            @Override
            public void receive(Adresse message) {
                result = message;
            }
        });
    }
}
