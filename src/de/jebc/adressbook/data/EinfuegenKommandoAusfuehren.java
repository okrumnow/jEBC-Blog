package de.jebc.adressbook.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ProcessWithException;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Schluessel;

public class EinfuegenKommandoAusfuehren extends
        ProcessWithException<Abfrage, Schluessel> {

    private final Connection conn;

    public EinfuegenKommandoAusfuehren(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void process(Abfrage message) {
        try {
            int identity = fuehreAbfrageAus(message);
            Schluessel result = new Schluessel(identity);
            Result().send(result);
        } catch (SQLException e) {
            Exception().send(e);
        }
    }

    private int fuehreAbfrageAus(Abfrage message) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(message.getQuery());
        ResultSet rs = stmt.getGeneratedKeys();
        int result = 0;
        while (rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }

}