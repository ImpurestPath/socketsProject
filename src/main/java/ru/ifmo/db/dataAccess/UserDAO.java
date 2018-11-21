package ru.ifmo.db.dataAccess;


import ru.ifmo.db.dataAccess.DTO.UserDTO;

public interface UserDAO {
    UserDTO get(String userName);
    int add(UserDTO userDTO);
    void update(int id, UserDTO userDTO);
    void delete(int id);
}
