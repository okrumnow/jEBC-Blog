package de.jebc.adressbook.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ProcessWithException;
import de.jebc.adressbook.domain.Abfrage;

public class KommandoAusfuehren extends ProcessWithException<Abfrage, Integer> {

    private final Connection conn;

    public KommandoAusfuehren(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    protected void process(Abfrage message) {
        try {
            int result = ausfuehren(message);
            Result().send(result);
        } catch (SQLException e) {
            Exception().send(e);
        }
    }

    private int ausfuehren(Abfrage message) throws SQLException {
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate(message.getQuery());
        return rows;
    }

}
