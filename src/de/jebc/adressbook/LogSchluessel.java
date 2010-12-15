package de.jebc.adressbook;

import org.slf4j.Logger;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.OutPinImpl;

public class LogSchluessel {

    private final Logger log;
    private OutPin<Schluessel> out = new OutPinImpl<Schluessel>();
    private InPin<Schluessel> in = new InPin<Schluessel>() {

        @Override
        public void receive(Schluessel message) {
            log.info("erstelle Abfrage für {}", message.getId());
            out.send(message);
        }
    };

    public LogSchluessel(Logger log) {
        this.log = log;
    }

    public InPin<Schluessel> In() {
        return in;
    }

    public OutPin<Schluessel> Out() {
        return out;
    }
}
