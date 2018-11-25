package ru.ifmo.db.gui.mappers;

import ru.ifmo.db.gui.*;

import java.util.List;

public class TransformerToGUI {
    public static Film toFilm(ru.ifmo.db.domain.guiServices.domainDTO.Film film, List<Actor> actors, List<Genre> genres){
        return new Film(film.getId(),film.getName(),film.getRegisseur(),film.getYear(),film.getRating(),actors,genres,film.getCosts(),film.getSubscriptions());
    }
    public static Actor toActor(ru.ifmo.db.domain.guiServices.domainDTO.Actor actor){
        return new Actor(actor.getId(),actor.getName());
    }
    public static Genre toGenre(ru.ifmo.db.domain.guiServices.domainDTO.Genre genre){
        return new Genre(genre.getId(),genre.getName());
    }
    public static Subscription toSubscription(ru.ifmo.db.domain.guiServices.domainDTO.Subscription subscription){
        return new Subscription(subscription.getId(),subscription.getName(),subscription.getFilms(),subscription.getCosts());
    }
    public static User toUser(ru.ifmo.db.domain.guiServices.domainDTO.User user){
        return new User(user.getId(),user.getUsername(),user.getBalance(),user.getFilms(),user.getSubscriptions());
    }
}
