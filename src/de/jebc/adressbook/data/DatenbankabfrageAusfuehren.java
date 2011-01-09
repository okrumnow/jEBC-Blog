package de.jebc.adressbook.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ProcessWithException;
import de.jebc.adressbook.domain.Abfrage;

public class DatenbankabfrageAusfuehren extends ProcessWithException<Abfrage, ResultSet> {

    private final Connection conn;

    public DatenbankabfrageAusfuehren(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void process(Abfrage message) {
        try {
            ResultSet rs = fuehreAbfrageAus(message);
            Result().send(rs);
        } catch (SQLException e) {
            Exception().send(e);
        }
    }

    private ResultSet fuehreAbfrageAus(Abfrage message) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(message.getQuery());
        return rs;
    }

}
