package de.jebc.adressbook.activities.speichern.neu;

import de.jebc.Process;
import de.jebc.adressbook.domain.Abfrage;
import de.jebc.adressbook.domain.Adresse;

public class EinfuegenKommandoErstellen extends Process<Adresse, Abfrage> {

    @Override
    protected void process(Adresse message) throws Exception {
        String command = String.format(
                "INSERT INTO Adressen (Name, Vorname, Anschrift, Telefon, Kategorie) "
                        + "VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s')",
                message.getName(), message.getVorname(),
                message.getAnschrift(), message.getTelefon(),
                message.getKategorie());
        Abfrage result = new Abfrage(command);
        Result().send(result);
    }

}
