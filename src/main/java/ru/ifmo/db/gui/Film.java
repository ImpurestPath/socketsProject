package ru.ifmo.db.gui;

public class Film {
    private final String name;
    private final String reggiseur;
    private final short year;
    private final short rating;

    public Film(String name, String reggiseur, short year, short rating) {
        this.name = name;
        this.reggiseur = reggiseur;
        this.year = year;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getReggiseur() {
        return reggiseur;
    }

    public short getYear() {
        return year;
    }

    public short getRating() {
        return rating;
    }
}
