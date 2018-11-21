package ru.ifmo.db;


import ru.ifmo.db.DTO.UserDTO;

public interface UserDAO {
    UserDTO get(String userName);
    int add(UserDTO userDTO);
    void update(int id, UserDTO userDTO);
    void delete(int id);
}
