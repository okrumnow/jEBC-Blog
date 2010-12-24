package de.jebc.examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import de.jebc.adressbook.Abfrage;

public class KlassischeException {

    private Connection conn;

    public ResultSet executeQuery(Abfrage message) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(message.getQuery());
        return rs;
    }
}
