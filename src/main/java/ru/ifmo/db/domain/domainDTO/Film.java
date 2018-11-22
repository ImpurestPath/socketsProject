package ru.ifmo.db.domain.domainDTO;

import ru.ifmo.db.domain.dataAccessDTO.FilmCostDTO;

import java.util.List;

public class Film {
    private class FilmCost{
        private final int id;
        private final int idFilm;
        private final int duration;
        private final double cost;
        public FilmCost(int id, int idFilm, int duration, double cost) {
            this.id = id;
            this.idFilm = idFilm;
            this.duration = duration;
            this.cost = cost;
        }
    }
    private final int id;
    private final String name;
    private final short year;
    private final String reggiseur;
    private final short rating;
    private final List<Integer> genres;
    private final List<Integer> actors;
    private final List<FilmCost> costs;
    private final List<Integer> subscriptions;

    public Film(
            int id,
            String name,
            short year,
            String reggiseur,
            short rating,
            List<Integer> genres,
            List<Integer> actors,
            List<FilmCost> costs,
            List<Integer> subscriptions) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.reggiseur = reggiseur;
        this.rating = rating;
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

    public String getReggiseur() {
        return reggiseur;
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

    public List<FilmCost> getCosts() {
        return costs;
    }

    public List<Integer> getSubscriptions() {
        return subscriptions;
    }
}
