package de.jebc.adressbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.OutPinImpl;

public class DatenbankabfrageAusfuehren {

    private InPin<Abfrage> inpin = new InPin<Abfrage>() {

        @Override
        public void receive(Abfrage message) {
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
    };
    private OutPin<ResultSet> outpin = new OutPinImpl<ResultSet>();
    private OutPin<Exception> exceptionPin = new OutPinImpl<Exception>();

    private final Connection conn;

    public DatenbankabfrageAusfuehren(Connection conn) {
        this.conn = conn;
    }

    public InPin<Abfrage> Start() {
        return inpin;
    }

    public OutPin<ResultSet> Result() {
        return outpin;
    }

    public OutPin<Exception> Exception() {
        return exceptionPin;
    }
}
