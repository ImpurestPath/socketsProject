package ru.ifmo.db.dataAccess;

import ru.ifmo.db.dataAccess.DTO.*;

public interface ConnectionDAO  {
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
    void deleteUserSubscription(int idUser, int idSubscription);
    void deleteUserFilm(int idUser, int idFilm);
    void deleteSubscription(int id);
    void deleteSubscriptionCost(int id);
    void deleteSubscriptionFilm(int idSubscription, int idFilm);
}
