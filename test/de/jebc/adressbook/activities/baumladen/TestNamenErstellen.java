package de.jebc.adressbook.activities.baumladen;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.adressbook.activities.baumladen.NamenErstellen;
import de.jebc.adressbook.domain.Name;

public class TestNamenErstellen {

    protected List<Name> result;
    protected Exception exception;

    @Test
    public void testErstellen() throws Exception {

        ResultSet rs = getResultSet();

        NamenErstellen sut = new NamenErstellen();
        storeResultPin(sut.Result());

        sut.Start().receive(rs);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
    
    @Test public void testExceptionWirdAnExceptionPinGereicht() throws Exception {
        
        ResultSet rs = getResultSetThrowingException();
        
        NamenErstellen sut = new NamenErstellen();
        storeExceptionPin(sut.Exception());

        sut.Start().receive(rs);

        assertNotNull(exception);
    }

    private void storeExceptionPin(OutPin<Exception> pin) {
        pin.wire(new InPin<Exception>() {
    
            @Override
            public void receive(Exception message) {
                exception = message;
            }
        });
    }

    private void storeResultPin(OutPin<List<Name>> pin) {
        pin.wire(new InPin<List<Name>>() {

            @Override
            public void receive(List<Name> message) {
                result = message;
            }
        });
    }

    private ResultSet getResultSet() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.next()).thenReturn(true, true, false);
        when(result.getInt("ID")).thenReturn(1, 2);
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

}
