package ru.ifmo.db.domain.mappers;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmDTO;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;

public class TransformerToDTO {
    public static FilmDTO toFilm(Film film){
        return new FilmDTO(film.getId(),film.getName(),film.getYear(),film.getReggiseur(),film.getRating());
    }

}
