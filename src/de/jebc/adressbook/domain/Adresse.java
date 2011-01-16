package de.jebc.adressbook.domain;

public class Adresse {

    private String kategorie;
    private String telefon;
    private String anschrift;
    private String vorname;
    private String name;
    private final Schluessel key;

    public Adresse(Schluessel key, String name, String vorname,
            String anschrift, String telefon, String kategorie) {
        this.key = key;
        this.name = name;
        this.vorname = vorname;
        this.anschrift = anschrift;
        this.telefon = telefon;
        this.kategorie = kategorie;
    }

    public Adresse() {
        this(new Schluessel(0), "", "", "", "", "");
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAnschrift() {
        return anschrift;
    }

    public void setAnschrift(String anschrift) {
        this.anschrift = anschrift;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schluessel getKey() {
        return key;
    }
}
