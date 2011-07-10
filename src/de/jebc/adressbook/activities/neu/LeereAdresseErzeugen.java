package de.jebc.adressbook.activities.neu;

import de.jebc.Process;
import de.jebc.adressbook.domain.Adresse;

public class LeereAdresseErzeugen extends Process<Void, Adresse> {

    @Override
    protected void process(Void message) throws Exception {
        Result().send(new Adresse());
    }

}
