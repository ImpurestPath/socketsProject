package ru.ifmo.db.dataAccess;


import ru.ifmo.db.dataAccess.DTO.UserDTO;

public interface UserDAO {
    UserDTO get(String userName);
    int add(UserDTO userDTO);
    void addFilm(int idUser, int idFilm);
    void addSubscription(int idUser, int idSubscription);
    void update(int id, UserDTO userDTO);
    void delete(int id);
    void deleteFilm(int idUser, int idFilm);
    void deleteSubscription(int idUser, int idSubscription);
}
