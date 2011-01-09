package de.jebc.adressbook.activities.baumladen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.jebc.ProcessWithException;
import de.jebc.adressbook.domain.Name;
import de.jebc.adressbook.domain.Schluessel;

public class NamenErstellen extends ProcessWithException<ResultSet, List<Name>> {

    @Override
    protected void process(ResultSet message) {
        List<Name> result = new ArrayList<Name>();
        try {
            while (message.next()) {
                Name name = new Name(new Schluessel(message.getInt("ID")), "",
                        "");
                result.add(name);
            }
            Result().send(result);
        } catch (SQLException e) {
            Exception().send(e);
        }
    }

}
