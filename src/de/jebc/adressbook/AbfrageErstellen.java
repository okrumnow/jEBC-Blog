package de.jebc.adressbook;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.OutPinImpl;

public class AbfrageErstellen {

    private InPin<Schluessel> inpin = new InPin<Schluessel>() {

        @Override
        public void receive(Schluessel message) {
            Abfrage result = create(message);
            outpin.send(result);
        }
    };
    private OutPin<Abfrage> outpin = new OutPinImpl<Abfrage>();

    public InPin<Schluessel> Start() {
        return inpin;
    }

    public OutPin<Abfrage> Result() {
        return outpin;
    }

    private Abfrage create(Schluessel key) {
        Abfrage result = new Abfrage(String.format(
                "SELECT * FROM Adressen WHERE id = %1$s", key.getId()));
        return result;
    }
}
