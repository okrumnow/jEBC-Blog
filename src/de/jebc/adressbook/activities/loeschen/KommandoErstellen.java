package de.jebc.adressbook.activities.loeschen;

import de.jebc.Process;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Schluessel;

public class KommandoErstellen extends Process<Schluessel, Abfrage> {

    @Override
    protected void process(Schluessel message) {
        Abfrage result = new Abfrage(String.format(
                "DELETE FROM Adressen WHERE id = %1$s", message.getId()));
        Result().send(result);
    }

}
