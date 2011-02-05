package de.jebc.adressbook.activities.speichern.neu;

import java.sql.Connection;

import de.jebc.BoardWithException;
import de.jebc.Broadcast;
import de.jebc.InPin;
import de.jebc.Join;
import de.jebc.OutPin;
import de.jebc.adressbook.data.EinfuegenKommandoAusfuehren;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;

public class AdresseAnlegen extends BoardWithException {

    private final Connection conn;
    private Broadcast<Adresse> adresseBroadcast;
    private EinfuegenKommandoErstellen kommandoErstellen;
    private EinfuegenKommandoAusfuehren kommandoAusfuehren;
    private Join<Adresse, Schluessel, Adresse> joinAdresse;

    public AdresseAnlegen(Connection conn) {
        this.conn = conn;
        createParts();
        wire();
    }

    public InPin<Adresse> Start() {
        return adresseBroadcast.In();
    }

    public OutPin<Adresse> Result() {
        return joinAdresse.Out();
    }

    private void wire() {
        wire(adresseBroadcast.Out(), kommandoErstellen.Start());
        wire(kommandoErstellen.Result(), kommandoAusfuehren.Start());
        join(adresseBroadcast.Out(), kommandoAusfuehren.Result(), joinAdresse);

        handle(kommandoAusfuehren.Exception());
    }

    private void createParts() {
        adresseBroadcast = new Broadcast<Adresse>();
        kommandoErstellen = new EinfuegenKommandoErstellen();
        kommandoAusfuehren = new EinfuegenKommandoAusfuehren(conn);
        joinAdresse = new Join<Adresse, Schluessel, Adresse>() {

            @Override
            protected Adresse join(Adresse adresse, Schluessel key) {
                Adresse result = new Adresse(key, adresse.getName(),
                        adresse.getVorname(), adresse.getAnschrift(),
                        adresse.getTelefon(), adresse.getKategorie());
                return result;
            }
        };
    }
}
