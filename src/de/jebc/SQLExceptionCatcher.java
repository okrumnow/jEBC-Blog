package de.jebc;

import java.sql.SQLException;

public class SQLExceptionCatcher<T> extends Process<T, T> {

	private OutPin<Exception> exceptionpin = new OutPinImpl<Exception>();

	public OutPin<Exception> Exception() {
		return exceptionpin;
	}

	@Override
	protected void process(T message) throws Exception {
		try {
			Result().send(message);
		} catch (SQLException se) {
			Exception().send(se);
		}
	}
}
