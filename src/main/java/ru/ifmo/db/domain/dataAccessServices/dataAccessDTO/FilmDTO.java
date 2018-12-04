package ru.ifmo.db.domain.dataAccessServices.dataAccessDTO;

import java.io.Serializable;

public class FilmDTO implements Serializable {
    private final int id;
    private final String name;
    private final short year;
    private final String reggiseur;
    private final short rating;
    private final int positiveReviews;
    private final int negativeReviews;
    private final int neutralReviews;

    public FilmDTO(
            int id,
            String name,
            short year,
            String reggiseur,
            short rating,
            int positiveReviews,
            int neutralReviews,
            int negativeReviews) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.reggiseur = reggiseur;
        this.rating = rating;
        this.positiveReviews = positiveReviews;
        this.negativeReviews = negativeReviews;
        this.neutralReviews = neutralReviews;
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

    public int getPositiveReviews() {
        return positiveReviews;
    }

    public int getNegativeReviews() {
        return negativeReviews;
    }

    public int getNeutralReviews() {
        return neutralReviews;
    }
}
