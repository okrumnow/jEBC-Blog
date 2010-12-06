package de.jebc.example2;

import de.jebc.InPin;
import de.jebc.OutPin;

public class AdresseEinlesen {

    private AbfrageErstellen abfrageErstellen = new AbfrageErstellen();
    private DatenbankabfrageAusfuehren abfrageAusfuehren = new DatenbankabfrageAusfuehren();
    private AdressobjektErstellen adresseErstellen = new AdressobjektErstellen();

    public AdresseEinlesen() {
        abfrageErstellen.Result().wire(abfrageAusfuehren.Start());
        abfrageAusfuehren.Result().wire(adresseErstellen.Start());
    }

    public InPin<Schluessel> Start() {
        return abfrageErstellen.Start();
    }

    public OutPin<Adresse> Result() {
        return adresseErstellen.Result();
    }
}
