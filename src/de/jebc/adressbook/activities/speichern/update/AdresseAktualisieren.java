package de.jebc.adressbook.activities.speichern.update;

import java.sql.Connection;

import de.jebc.BoardWithException;
import de.jebc.Broadcast;
import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.Valve;
import de.jebc.adressbook.activities.loeschen.ErgebnisPruefen;
import de.jebc.adressbook.data.KommandoAusfuehren;
import de.jebc.adressbook.domain.Adresse;

public class AdresseAktualisieren extends BoardWithException {

    private final Connection conn;
    private KommandoErstellen kommandoErstellen;
    private KommandoAusfuehren kommandoAusfuehren;
    private ErgebnisPruefen ergebnisPruefen;
    private Valve<Adresse> valve;
    private Broadcast<Adresse> adresseBroadcast;

    public AdresseAktualisieren(Connection conn) {
        this.conn = conn;
        createParts();
        wire();
    }

    public InPin<Adresse> Start() {
        return adresseBroadcast.In();
    }

    public OutPin<Adresse> Result() {
        return valve.Out();
    }

    private void wire() {
        wire(adresseBroadcast.Out(), kommandoErstellen.Start());
        wire(kommandoErstellen.Result(), kommandoAusfuehren.Start());
        wire(kommandoAusfuehren.Result(), ergebnisPruefen.Start());

        join(adresseBroadcast.Out(), ergebnisPruefen.Result(), valve);
        
        handle(kommandoAusfuehren.Exception());
        handle(ergebnisPruefen.Exception());
    }

    private void createParts() {
        kommandoErstellen = new KommandoErstellen();
        kommandoAusfuehren = new KommandoAusfuehren(conn);
        ergebnisPruefen = new ErgebnisPruefen();
        adresseBroadcast = new Broadcast<Adresse>();
        valve = new Valve<Adresse>();
    }
}
