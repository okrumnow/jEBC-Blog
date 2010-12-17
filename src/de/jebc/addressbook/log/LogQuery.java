package de.jebc.addressbook.log;

import org.slf4j.Logger;

import de.jebc.addressbook.Query;
import de.jebc.log.LogInfo;

public class LogQuery extends LogInfo<Query> {

    public LogQuery(Logger log) {
        super(log);
    }

    @Override
    protected String getMessage(Query message) {
        return "Query is: " + message.getQuery();
    }

}
