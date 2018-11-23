package ru.ifmo.db.gui;

import java.util.List;

public class Film {
    private final String name;
    private final String reggiseur;
    private final short year;
    private final short rating;
    private final List<Actor> actors;
    private final List<Genre> genres;

    public Film(String name, String reggiseur, short year, short rating, List<Actor> actors, List<Genre> genres) {
        this.name = name;
        this.reggiseur = reggiseur;
        this.year = year;
        this.rating = rating;
        this.actors = actors;
        this.genres = genres;
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

    public List<Actor> getActors() {
        return actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
