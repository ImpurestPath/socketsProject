package ru.ifmo.db.domain.mappers;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.*;
import ru.ifmo.db.domain.guiServices.domainDTO.*;

import java.util.List;

public class TransformerToEntity {
    public static Film toFilm(FilmDTO film, List<Integer> actors, List<Integer> genres, List<Integer> subscriptions, List<FilmCostDTO> filmCosts) {
        return new Film(film.getId(),
                film.getName(),
                film.getYear(),
                film.getReggiseur(),
                film.getRating(),
                film.getPositiveReviews(),
                film.getNeutralReviews(),
                film.getNegativeReviews(),
                genres,
                actors,
                filmCosts,
                subscriptions);
    }

    public static Subscription toSubscription(SubscriptionDTO subscriptionDTO, List<Integer> films, List<SubscriptionCostDTO> costs) {
        return new Subscription(subscriptionDTO.getId(), subscriptionDTO.getName(), films, costs);
    }

    public static User toUser(UserDTO userDTO, List<UserPurchaseDTO> films, List<UserPurchaseDTO> subscriptions) {
        return new User(userDTO.getId(), userDTO.getUserName(), userDTO.getBalance(), films, subscriptions);
    }

    public static Actor toActor(ActorDTO actorDTO) {
        return new Actor(actorDTO.getId(), actorDTO.getName());
    }

    public static Genre toGenre(GenreDTO genreDTO) {
        return new Genre(genreDTO.getId(), genreDTO.getName());
    }
}
