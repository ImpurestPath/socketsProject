package ru.ifmo.db.gui;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;

import java.util.List;

public class Film {
    private final int id;
    private final String name;
    private final String reggiseur;
    private final short year;
    private final short rating;
    private final int positiveReviews;
    private final int neutralReviews;
    private final int negativeReviews;
    private final List<Actor> actors;
    private final List<Genre> genres;
    private final List<FilmCostDTO> costs;
    private final List<Integer> subscriptions;

    public Film(int id,
                String name,
                String reggiseur,
                short year,
                short rating,
                int positiveReviews,
                int neutralReviews,
                int negativeReviews,
                List<Actor> actors,
                List<Genre> genres,
                List<FilmCostDTO> costs,
                List<Integer> subscriptions) {
        this.id = id;
        this.name = name;
        this.reggiseur = reggiseur;
        this.year = year;
        this.rating = rating;
        this.positiveReviews = positiveReviews;
        this.neutralReviews = neutralReviews;
        this.negativeReviews = negativeReviews;
        this.actors = actors;
        this.genres = genres;
        this.costs = costs;
        this.subscriptions = subscriptions;
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

    public int getId() {
        return id;
    }

    public List<FilmCostDTO> getCosts() {
        return costs;
    }

    public List<Integer> getSubscriptions() {
        return subscriptions;
    }

    public int getPositiveReviews() {
        return positiveReviews;
    }

    public int getNeutralReviews() {
        return neutralReviews;
    }

    public int getNegativeReviews() {
        return negativeReviews;
    }
}
