package de.jebc.adressbook;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.Board;
import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.SQLExceptionCatcher;
import de.jebc.Watcher;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;
import de.jebc.log.LogError;

public class Main extends Board {

	Logger log = LoggerFactory.getLogger(Main.class);
	private AdresseEinlesen adresse;
	private SQLExceptionCatcher<Schluessel> catcher;
	private Connection conn = null;
	
	public Main() {
		createParts();
		wire();
	}

    public InPin<Schluessel> Start() {
        return catcher.Start();
    }

    public OutPin<Adresse> Result() {
        return adresse.Result();
    }

	private void createParts() {
		adresse = new AdresseEinlesen(conn);
		catcher = new SQLExceptionCatcher<Schluessel>();
	}

	private void wire() {
		wire(catcher.Result(), adresse.Start());
		wire(catcher.Exception(), logException.In());
	}
	
	private Watcher<Exception> logException = new LogError<Exception>(log) {

		@Override
		protected String getMessage(Exception message) {
			return message.toString();
		}
		
	};
}
