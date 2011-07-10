package de.jebc.adressbook.activities.speichern.update;

import de.jebc.Process;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Adresse;

public class KommandoErstellen extends Process<Adresse, Abfrage> {

    @Override
    protected void process(Adresse message) throws Exception {
        String query = String
                .format("UPDATE Adressen SET Name = '%1$s', Vorname = '%2$s', Anschrift = '%3$s', Telefon = '%4$s', Kategorie = '%5$s' WHERE ID = %6$d",
                        message.getName(), message.getVorname(),
                        message.getAnschrift(), message.getTelefon(),
                        message.getKategorie(), message.getKey().getId());
        Abfrage result = new Abfrage(query);
        Result().send(result);
    }

}
