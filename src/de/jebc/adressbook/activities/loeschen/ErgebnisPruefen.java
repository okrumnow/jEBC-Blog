package de.jebc.adressbook.activities.loeschen;

import de.jebc.ProcessWithException;

public class ErgebnisPruefen extends ProcessWithException<Integer, Void> {

    @Override
    protected void process(Integer message) throws Exception {
        if (message == 1) {
            Result().send(null);
        } else {
            Exception()
                    .send(new Exception(
                            "Falsche Anzahl Zeilen als Ergebnis des Kommandos"));
        }
    }

}
