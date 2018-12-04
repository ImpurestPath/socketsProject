package ru.ifmo.db.gui.mappers;

import ru.ifmo.db.gui.*;

import java.util.ArrayList;
import java.util.List;

public class TransformerToEntity {
    public static ru.ifmo.db.domain.guiServices.domainDTO.Film toFilm(Film film){
        List<Integer> actors = new ArrayList<>();
        List<Integer> genres = new ArrayList<>();
        for (Actor actor : film.getActors()) actors.add(actor.getId());
        for (Genre genre : film.getGenres()) genres.add(genre.getId());
        return  new ru.ifmo.db.domain.guiServices.domainDTO.Film(
                film.getId(),
                film.getName(),
                film.getYear(),
                film.getReggiseur(),
                film.getRating(),
                film.getPositiveReviews(),
                film.getNeutralReviews(),
                film.getNegativeReviews(),
                genres,
                actors,
                film.getCosts(),
                film.getSubscriptions());
    }
    public static ru.ifmo.db.domain.guiServices.domainDTO.Actor toActor(Actor actor){
        return new ru.ifmo.db.domain.guiServices.domainDTO.Actor(actor.getId(),actor.getName());
    }
    public static ru.ifmo.db.domain.guiServices.domainDTO.Genre toGenre(Genre genre){
        return new ru.ifmo.db.domain.guiServices.domainDTO.Genre(genre.getId(),genre.getName());
    }
    public static ru.ifmo.db.domain.guiServices.domainDTO.Subscription toSubscription(Subscription subscription){
        return new ru.ifmo.db.domain.guiServices.domainDTO.Subscription(
                subscription.getId(),
                subscription.getName(),
                subscription.getFilms(),
                subscription.getCosts());
    }
    public static ru.ifmo.db.domain.guiServices.domainDTO.User toUser(User user){
        return new ru.ifmo.db.domain.guiServices.domainDTO.User(
                user.getId(),
                user.getUsername(),
                user.getBalance(),
                user.getFilms(),
                user.getSubscriptions());
    }
}
