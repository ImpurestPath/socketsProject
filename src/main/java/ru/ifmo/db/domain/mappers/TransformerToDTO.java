package ru.ifmo.db.domain.mappers;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.*;
import ru.ifmo.db.domain.guiServices.domainDTO.*;

public class TransformerToDTO {
    public static FilmDTO toFilm(Film film){
        return new FilmDTO(film.getId(),film.getName(),film.getYear(),film.getRegisseur(),film.getRating());
    }
    public static SubscriptionDTO toSubscription(Subscription subscription){
        return new SubscriptionDTO(subscription.getId(),subscription.getName());
    }
    public static UserDTO toUser(User user){
        return new UserDTO(user.getId(),user.getUsername(),user.getBalance());
    }
    public static ActorDTO toActor(Actor actor){
        return new ActorDTO(actor.getId(),actor.getName());
    }
    public static GenreDTO toGenre(Genre genre){
        return new GenreDTO(genre.getId(),genre.getName());
    }
}
