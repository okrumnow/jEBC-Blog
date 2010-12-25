package de.jebc.adressbook;

import de.jebc.Process;

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
