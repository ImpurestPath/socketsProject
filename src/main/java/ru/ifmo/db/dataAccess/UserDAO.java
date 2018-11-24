package ru.ifmo.db.dataAccess;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserDTO;

public interface UserDAO {
    UserDTO getByName(String userName);
    int add(UserDTO userDTO);
    void addFilm(int idUser, int idFilm);
    void addSubscription(int idUser, int idSubscription);
    void update(int id, UserDTO userDTO);
    void delete(int id);
    void deleteFilm(int idUser, int idFilm);
    void deleteSubscription(int idUser, int idSubscription);
}
