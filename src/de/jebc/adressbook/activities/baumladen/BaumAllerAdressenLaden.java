package de.jebc.adressbook.activities.baumladen;

import java.sql.Connection;

import javax.swing.tree.TreeModel;

import de.jebc.BoardWithException;
import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.adressbook.data.DatenbankabfrageAusfuehren;

public class BaumAllerAdressenLaden extends BoardWithException {

    private final Connection conn;
    private AbfrageErstellen abfrageErstellen;
    private DatenbankabfrageAusfuehren abfrageAusfuehren;
    private NamenErstellen namenErstellen;
    private BaumErstellen baumErstellen;

    public BaumAllerAdressenLaden(Connection conn) {
        this.conn = conn;
        createParts();
        wire();
    }

    public InPin<Void> Start() {
        return abfrageErstellen.Start();
    }

    public OutPin<TreeModel> Result() {
        return baumErstellen.Result();
    }

    private void createParts() {
        abfrageErstellen = new AbfrageErstellen();
        abfrageAusfuehren = new DatenbankabfrageAusfuehren(conn);
        namenErstellen = new NamenErstellen();
        baumErstellen = new BaumErstellen();
    }

    private void wire() {
        wire(abfrageErstellen.Result(), abfrageAusfuehren.Start());
        wire(abfrageAusfuehren.Result(), namenErstellen.Start());
        wire(namenErstellen.Result(), baumErstellen.Start());

        handle(abfrageAusfuehren.Exception());
        handle(namenErstellen.Exception());
    }
}
