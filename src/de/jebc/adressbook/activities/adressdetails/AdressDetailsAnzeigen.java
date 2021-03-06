package de.jebc.adressbook.activities.adressdetails;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.BoardWithException;
import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.Watcher;
import de.jebc.adressbook.activities.SelektierteAdresseBestimmen;
import de.jebc.adressbook.data.DatenbankabfrageAusfuehren;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Name;
import de.jebc.adressbook.domain.Schluessel;
import de.jebc.log.LogDebug;
import de.jebc.log.LogInfo;

public class AdressDetailsAnzeigen extends BoardWithException {

    Logger log = LoggerFactory.getLogger(AdressDetailsAnzeigen.class);
    private AbfrageErstellen abfrageErstellen;
    private DatenbankabfrageAusfuehren abfrageAusfuehren;
    private AdressobjektErstellen adresseErstellen;
    private SelektierteAdresseBestimmen adresseBestimmen;
    private final Connection conn;

    public AdressDetailsAnzeigen(Connection conn) {
        this.conn = conn;
        createParts();
        wire();
    }

    private void wire() {
        wire(adresseBestimmen.Result(), abfrageErstellen.Start(),
                watcher(logSchluessel));
        wire(abfrageErstellen.Result(), abfrageAusfuehren.Start(),
                watcher(logAbfrage));
        wire(abfrageAusfuehren.Result(), adresseErstellen.Start());

        handle(abfrageAusfuehren.Exception());
        handle(adresseErstellen.Exception());
    }

    private void createParts() {
        adresseBestimmen = new SelektierteAdresseBestimmen();
        abfrageErstellen = new AbfrageErstellen();
        abfrageAusfuehren = new DatenbankabfrageAusfuehren(conn);
        adresseErstellen = new AdressobjektErstellen();
    }

    public InPin<Name> Start() {
        return adresseBestimmen.Start();
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
    private Watcher<Abfrage> logAbfrage = new LogInfo<Abfrage>(log) {

        @Override
        protected String getMessage(Abfrage message) {
            return "Abfrage ist: " + message.getQuery();
        }

    };

}
