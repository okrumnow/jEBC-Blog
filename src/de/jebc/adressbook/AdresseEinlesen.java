package de.jebc.adressbook;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.Watcher;
import de.jebc.adressbook.log.LogAbfrage;
import de.jebc.log.LogDebug;

public class AdresseEinlesen {

    Logger log = LoggerFactory.getLogger(AdresseEinlesen.class);
    private AbfrageErstellen abfrageErstellen = new AbfrageErstellen();
    private DatenbankabfrageAusfuehren abfrageAusfuehren;
    private AdressobjektErstellen adresseErstellen = new AdressobjektErstellen();

    public AdresseEinlesen(Connection conn) {
        abfrageAusfuehren = new DatenbankabfrageAusfuehren(conn);
        logSchluessel.Out().wire(abfrageErstellen.Start());
        abfrageErstellen.Result().wire(logAbfrage.In());
        logAbfrage.Out().wire(abfrageAusfuehren.Start());
        abfrageAusfuehren.Result().wire(adresseErstellen.Start());
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
    private Watcher<Abfrage> logAbfrage = new LogAbfrage(log);

}
