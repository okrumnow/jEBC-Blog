package de.jebc.adressbook.activities.baumladen;

import de.jebc.Process;
import de.jebc.adressbook.domain.Abfrage;

public class AbfrageErstellen extends Process<Void, Abfrage> {

    @Override
    protected void process(Void message) {
        Abfrage result = new Abfrage("SELECT * FROM Adressen");
        Result().send(result);
    }

}
