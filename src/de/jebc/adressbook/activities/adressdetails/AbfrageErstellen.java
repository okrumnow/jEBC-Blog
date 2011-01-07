package de.jebc.adressbook.activities.adressdetails;

import de.jebc.Process;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Schluessel;

public class AbfrageErstellen extends Process<Schluessel, Abfrage> {

    @Override
    protected void process(Schluessel message) {
        Abfrage result = create(message);
        Result().send(result);
    }

    private Abfrage create(Schluessel key) {
        Abfrage result = new Abfrage(String.format(
                "SELECT * FROM Adressen WHERE id = %1$s", key.getId()));
        return result;
    }
}
