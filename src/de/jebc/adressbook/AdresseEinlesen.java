package de.jebc.adressbook;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.Board;
import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.Watcher;
import de.jebc.adressbook.activities.adressdetails.AdressobjektErstellen;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;
import de.jebc.log.LogDebug;

public class AdresseEinlesen extends Board {

	Logger log = LoggerFactory.getLogger(AdresseEinlesen.class);
	private AbfrageErstellen abfrageErstellen = new AbfrageErstellen();
	private DatenbankabfrageAusfuehren abfrageAusfuehren;
	private AdressobjektErstellen adresseErstellen = new AdressobjektErstellen();
	private final Connection conn;

	public AdresseEinlesen(Connection conn) {
		this.conn = conn;
		createParts();
		wire();
	}

	private void wire() {
		wire(abfrageErstellen.Start(), watcher(logSchluessel));
		wire(abfrageErstellen.Result(), abfrageAusfuehren.Start(),
				watcher(logAbfrage));
		wire(abfrageAusfuehren.Result(), adresseErstellen.Start());
	}

	private void createParts() {
		abfrageAusfuehren = new DatenbankabfrageAusfuehren(conn);
	}

	public InPin<Schluessel> Start() {
		return logSchluessel.In();
	}

	public OutPin<Adresse> Result() {
		return adresseErstellen.Result();
	}

	private Watcher<Schluessel> logSchluessel = new LogDebug<Schluessel>(log) {

		@Override
		protected String getMessage(Schluessel message) {
			return "erstelle Abfrage für " + message.getId();
		}
	};
	private Watcher<Abfrage> logAbfrage = new LogDebug<Abfrage>(log) {

		@Override
		protected String getMessage(Abfrage message) {
			return "Abfrage: " + message.toString();
		}
	};

}
