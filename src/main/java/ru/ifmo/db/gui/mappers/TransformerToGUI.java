package ru.ifmo.db.gui.mappers;

import ru.ifmo.db.gui.Actor;
import ru.ifmo.db.gui.Film;
import ru.ifmo.db.gui.Genre;

import java.util.List;

public class TransformerToGUI {
    public static Film toFilm(ru.ifmo.db.domain.guiServices.domainDTO.Film film, List<Actor> actors, List<Genre> genres){
        return new Film(film.getName(),film.getRegisseur(),film.getYear(),film.getRating(),actors,genres);
    }
    public static Actor toActor(ru.ifmo.db.domain.guiServices.domainDTO.Actor actor){
        return new Actor(actor.getId(),actor.getName());
    }
    public static Genre toGenre(ru.ifmo.db.domain.guiServices.domainDTO.Genre genre){
        return new Genre(genre.getId(),genre.getName());
    }
}
