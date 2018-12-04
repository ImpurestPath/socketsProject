package ru.ifmo.db.domain.dataAccessServices.dataAccessDTO;

import ru.ifmo.db.domain.Cost;

import java.io.Serializable;
import java.util.Objects;

public class FilmCostDTO implements Serializable, Cost {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCostDTO that = (FilmCostDTO) o;
        return id == that.id &&
                idFilm == that.idFilm &&
                duration == that.duration &&
                Double.compare(that.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idFilm, duration, cost);
    }
}
