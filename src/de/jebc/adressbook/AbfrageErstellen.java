package de.jebc.adressbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private Logger logger = LoggerFactory.getLogger(AbfrageErstellen.class);

    private Abfrage create(Schluessel key) {
        logger.info("erstelle Abfrage für {}", key.getId());
        Abfrage result = new Abfrage(String.format(
                "SELECT * FROM Adressen WHERE id = %1$s", key.getId()));
        logger.info("Abfrage ist: {}", result.getQuery());
        return result;
    }
}
