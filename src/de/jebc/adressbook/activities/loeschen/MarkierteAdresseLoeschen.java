package de.jebc.adressbook.activities.loeschen;

import java.sql.Connection;

import de.jebc.BoardWithException;
import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.adressbook.activities.SelektierteAdresseBestimmen;
import de.jebc.adressbook.data.KommandoAusfuehren;
import de.jebc.adressbook.domain.Name;

public class MarkierteAdresseLoeschen extends BoardWithException {

    private final Connection conn;
    private SelektierteAdresseBestimmen adresseBestimmen;
    private KommandoErstellen kommandoErstellen;
    private KommandoAusfuehren kommandoAusfuehren;
    private ErgebnisPruefen ergebnisPruefen;

    public MarkierteAdresseLoeschen(Connection conn) {
        this.conn = conn;
        createParts();
        wire();
    }

    public InPin<Name> Start() {
        return adresseBestimmen.Start();
    }

    public OutPin<Void> Result() {
        return ergebnisPruefen.Result();
    }

    private void wire() {
        wire(adresseBestimmen.Result(), kommandoErstellen.Start());
        wire(kommandoErstellen.Result(), kommandoAusfuehren.Start());
        wire(kommandoAusfuehren.Result(), ergebnisPruefen.Start());

        handle(kommandoAusfuehren.Exception());
        handle(ergebnisPruefen.Exception());
    }

    private void createParts() {
        adresseBestimmen = new SelektierteAdresseBestimmen();
        kommandoErstellen = new KommandoErstellen();
        kommandoAusfuehren = new KommandoAusfuehren(conn);
        ergebnisPruefen = new ErgebnisPruefen();
    }
}
