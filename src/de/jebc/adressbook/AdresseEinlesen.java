package de.jebc.adressbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.adressbook.log.LogAbfrage;
import de.jebc.adressbook.log.LogSchluessel;

public class AdresseEinlesen {

    Logger log = LoggerFactory.getLogger(AdresseEinlesen.class);
    private AbfrageErstellen abfrageErstellen = new AbfrageErstellen();
    private DatenbankabfrageAusfuehren abfrageAusfuehren = new DatenbankabfrageAusfuehren();
    private AdressobjektErstellen adresseErstellen = new AdressobjektErstellen();
    private LogSchluessel logSchluessel = new LogSchluessel(log);
    private LogAbfrage logAbfrage = new LogAbfrage(log);

    public AdresseEinlesen() {
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
}
