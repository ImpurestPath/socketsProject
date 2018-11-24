package ru.ifmo.db.domain.mappers;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.ActorDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmDTO;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;

import java.util.List;

public class TransformerToEntity {
    public static Film toFilm(FilmDTO film, List<Integer> actors, List<Integer> genres, List<Integer> subscriptions, List<FilmCostDTO> filmCosts){
        return new Film(film.getId(),film.getName(),film.getYear(),film.getReggiseur(),film.getRating(),genres,actors,filmCosts,subscriptions);
    }
}
