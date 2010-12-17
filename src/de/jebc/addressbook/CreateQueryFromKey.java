package de.jebc.addressbook;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.OutPinImpl;

public class CreateQueryFromKey {

    private InPin<Key> inpin = new InPin<Key>() {

        @Override
        public void receive(Key message) {
            Query result = createQueryFrom(message);
            outpin.send(result);
        }
    };
    private OutPin<Query> outpin = new OutPinImpl<Query>();

    public InPin<Key> Start() {
        return inpin;
    }

    public OutPin<Query> Result() {
        return outpin;
    }

    private Query createQueryFrom(Key key) {
        Query result = new Query(String.format(
                "SELECT * FROM Adressen WHERE id = %1$s", key.getId()));
        return result;
    }
}
