package de.jebc.adressbook.activities;

import de.jebc.Process;
import de.jebc.adressbook.domain.Name;
import de.jebc.adressbook.domain.Schluessel;

public class SelektierteAdresseBestimmen extends Process<Name, Schluessel> {

    @Override
    protected void process(Name message) {
        Result().send(message.getSchluessel());
    }

}
