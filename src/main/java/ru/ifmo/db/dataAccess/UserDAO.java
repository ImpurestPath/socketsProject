package ru.ifmo.db.dataAccess;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserDTO;

public interface UserDAO extends DAO<UserDTO>{
    UserDTO getByName(String userName);
}
