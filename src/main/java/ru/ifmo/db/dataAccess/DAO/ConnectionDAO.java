package ru.ifmo.db.dataAccess.DAO;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.*;

import java.util.List;

public interface ConnectionDAO {
    //TODO delete id from update
    int addFilm(FilmDTO dto);

    void addFilmGenre(int idFilm, int idGenre);

    int addFilmCost(FilmCostDTO dto);

    void addFilmActor(int idFilm, int idActor);

    int addActor(ActorDTO dto);

    int addGenre(GenreDTO dto);

    int addUser(UserDTO dto);

    void addUserSubscription(int idUser, int idSubscriptionCost);

    void addUserFilm(int idUser, int idFilmCost);

    int addSubscription(SubscriptionDTO dto);

    int addSubscriptionCost(SubscriptionCostDTO dto);

    void addSubscriptionFilm(int idSubscription, int idFilm);

    void updateFilm(int id, FilmDTO filmDTO);

    void updateFilmCost(int id, FilmCostDTO dto);

    void updateActor(int id, ActorDTO dto);

    void updateGenre(int id, GenreDTO dto);

    void updateUser(int id, UserDTO dto);

    void updateSubscription(int id, SubscriptionDTO dto);

    void updateSubscriptionCost(int id, SubscriptionCostDTO dto);

    void deleteFilm(int id);

    void deleteFilmGenre(int idFilm, int idGenre);

    void deleteFilmCost(int id);

    void deleteFilmActor(int idFilm, int idActor);

    void deleteActor(int id);

    void deleteGenre(int id);

    void deleteUser(int id);

    void deleteUserSubscription(UserPurchaseDTO dto);

    void deleteUserFilm(UserPurchaseDTO dto);

    void deleteSubscription(int id);

    void deleteSubscriptionCost(int id);

    void deleteSubscriptionFilm(int idSubscription, int idFilm);

    List<FilmDTO> getAllFilms();

    FilmDTO getFilm(int id);

    List<FilmCostDTO> getAllFilmCosts(int idFilm);

    FilmCostDTO getFilmCost(int idFilmCost);

    List<Integer> getFilmSubscriptions(int idFilm);

    List<Integer> getFilmActors(int idFilm);

    List<Integer> getFilmGenres(int idFilm);

    ActorDTO getActor(int id);

    GenreDTO getGenre(int id);

    List<SubscriptionDTO> getAllSubscriptions();

    SubscriptionDTO getSubscription(int id);

    List<SubscriptionCostDTO> getAllSubscriptionCosts(int idSubscription);

    SubscriptionCostDTO getSubscriptionCost(int idSubscriptionCost);

    List<Integer> getSubscriptionFilms(int idSubscription);

    UserDTO getUser(String userName);

    List<UserPurchaseDTO> getUserFilms(int idUser);

    List<UserPurchaseDTO> getUserSubscriptions(int idUser);
}
