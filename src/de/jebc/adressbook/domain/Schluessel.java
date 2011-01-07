package de.jebc.adressbook.domain;

public class Schluessel {

    private final int id;

    public Schluessel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Schluessel other = (Schluessel) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
