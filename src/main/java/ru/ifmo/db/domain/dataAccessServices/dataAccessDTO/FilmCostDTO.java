package ru.ifmo.db.domain.dataAccessServices.dataAccessDTO;

import java.io.Serializable;

public class FilmCostDTO implements Serializable {
    private final int id;
    private final int idFilm;
    private final int duration;
    private final double cost;


    public FilmCostDTO(int id, int idFilm, int duration, double cost) {
        this.id = id;
        this.idFilm = idFilm;
        this.duration = duration;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public int getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }
}
