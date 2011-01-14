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
        try {
            List<Name> result = createNames(message);
            Result().send(result);
        } catch (SQLException e) {
            Exception().send(e);
        }
    }

    private List<Name> createNames(ResultSet message) throws SQLException {
        List<Name> result = new ArrayList<Name>();
        while (message.next()) {
            Name name = createName(message);
            result.add(name);
        }
        return result;
    }

    private Name createName(ResultSet message) throws SQLException {
        Name name = new Name(new Schluessel(message.getInt("ID")),
                String.format("%1$s, %2$s", message.getString("Name"),
                        message.getString("Vorname")),
                message.getString("Kategorie"));
        return name;
    }

}
