package de.jebc.adressbook;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.jebc.OutPin;
import de.jebc.OutPinImpl;
import de.jebc.Process;
import de.jebc.adressbook.domain.Adresse;
import de.jebc.adressbook.domain.Schluessel;

public class AdressobjektErstellen extends Process<ResultSet, Adresse> {

    private OutPin<Exception> exceptionPin = new OutPinImpl<Exception>();

    @Override
    protected void process(ResultSet message) {
        try {
            if (message.next()) {
                int id = message.getInt("ID");
                String name = message.getString("Name");
                String vorname = message.getString("Vorname");
                String anschrift = message.getString("Anschrift");
                String telefon = message.getString("Telefon");
                String kategorie = message.getString("Kategorie");
                Adresse result = new Adresse(new Schluessel(id), name, vorname,
                        anschrift, telefon, kategorie);
                Result().send(result);
            } else {
                Exception().send(
                        new IllegalArgumentException("ResultSet is empty"));
            }
        } catch (SQLException e) {
            Exception().send(e);
        }

    }

    public OutPin<Exception> Exception() {
        return exceptionPin;
    }

}
