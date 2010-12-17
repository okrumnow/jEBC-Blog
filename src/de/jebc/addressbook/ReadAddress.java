package de.jebc.addressbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.InPin;
import de.jebc.OutPin;
import de.jebc.Watcher;
import de.jebc.addressbook.log.LogQuery;
import de.jebc.log.LogDebug;

public class ReadAddress {

    Logger log = LoggerFactory.getLogger(ReadAddress.class);
    private CreateQueryFromKey createQueryFromKey = new CreateQueryFromKey();
    private ExecuteDatabaseQuery executeQuery = new ExecuteDatabaseQuery();
    private CreateAddress createAddress = new CreateAddress();

    public ReadAddress() {
        logKey.Out().wire(createQueryFromKey.Start());
        createQueryFromKey.Result().wire(logQuery.In());
        logQuery.Out().wire(executeQuery.Start());
        executeQuery.Result().wire(createAddress.Start());
    }

    public InPin<Key> Start() {
        return logKey.In();
    }

    public OutPin<Address> Result() {
        return createAddress.Result();
    }

    private Watcher<Key> logKey = new LogDebug<Key>(log) {

        @Override
        protected String getMessage(Key message) {
            return "create Query for key " + message.getId();
        }
    };
    private Watcher<Query> logQuery = new LogQuery(log);

}
