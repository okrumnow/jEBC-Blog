package de.jebc.adressbook;

import org.slf4j.Logger;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.OutPinImpl;

public class LogAbfrage {

    private final Logger log;
    private OutPin<Abfrage> out = new OutPinImpl<Abfrage>();
    private InPin<Abfrage> in = new InPin<Abfrage>() {

        @Override
        public void receive(Abfrage message) {
            log.info("Abfrage ist: {}", message.getQuery());
            out.send(message);
        }
    };

    public LogAbfrage(Logger log) {
        this.log = log;
    }

    public InPin<Abfrage> In() {
        return in;
    }

    public OutPin<Abfrage> Out() {
        return out;
    }
}
