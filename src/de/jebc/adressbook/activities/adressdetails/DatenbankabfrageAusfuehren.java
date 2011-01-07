package de.jebc.adressbook.activities.adressdetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.Process;
import de.jebc.OutPin;
import de.jebc.OutPinImpl;
import de.jebc.adressbook.domain.Abfrage;

public class DatenbankabfrageAusfuehren extends Process<Abfrage, ResultSet> {

    private OutPin<Exception> exceptionPin = new OutPinImpl<Exception>();

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

    public OutPin<Exception> Exception() {
        return exceptionPin;
    }
}
