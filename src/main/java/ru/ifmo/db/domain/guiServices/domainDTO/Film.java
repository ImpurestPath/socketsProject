package ru.ifmo.db.domain.guiServices.domainDTO;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;

import java.io.Serializable;
import java.util.List;
public class Film  implements Serializable,hasId {

    private final int id;
    private final String name;
    private final short year;
    private final String regisseur;
    private final short rating;
    private final int positiveReviews;
    private final int neutralReviews;
    private final int negativeReviews;
    private final List<Integer> genres;
    private final List<Integer> actors;
    private final List<FilmCostDTO> costs;
    private final List<Integer> subscriptions;

    public Film(int id,
                String name,
                short year,
                String regisseur,
                short rating,
                int positiveReviews,
                int neutralReviews,
                int negativeReviews,
                List<Integer> genres,
                List<Integer> actors,
                List<FilmCostDTO> costs,
                List<Integer> subscriptions) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.regisseur = regisseur;
        this.rating = rating;
        this.positiveReviews = positiveReviews;
        this.neutralReviews = neutralReviews;
        this.negativeReviews = negativeReviews;
        this.genres = genres;
        this.actors = actors;
        this.costs = costs;
        this.subscriptions = subscriptions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public short getYear() {
        return year;
    }

    public String getRegisseur() {
        return regisseur;
    }

    public short getRating() {
        return rating;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public List<Integer> getActors() {
        return actors;
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
