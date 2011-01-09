package de.jebc.adressbook.domain;

public class Name {

    private final Schluessel schluessel;
    private final String name;
    private final String kategorie;

    public Name(Schluessel schluessel, String name, String kategorie) {
        this.schluessel = schluessel;
        this.name = name;
        this.kategorie = kategorie;
    }

    public Schluessel getSchluessel() {
        return schluessel;
    }

    public String getName() {
        return name;
    }

    public String getKategorie() {
        return kategorie;
    }

}
