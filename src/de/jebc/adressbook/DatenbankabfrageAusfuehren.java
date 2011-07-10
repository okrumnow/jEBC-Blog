package de.jebc.adressbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.Process;

public class DatenbankabfrageAusfuehren extends Process<Abfrage, ResultSet> {

	private final Connection conn;

	public DatenbankabfrageAusfuehren(Connection conn) {
		this.conn = conn;
	}

	@Override
	protected void process(Abfrage message) throws Exception {
		ResultSet rs = fuehreAbfrageAus(message);
		Result().send(rs);
	}

	private ResultSet fuehreAbfrageAus(Abfrage message) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(message.getQuery());
		return rs;
	}
}
