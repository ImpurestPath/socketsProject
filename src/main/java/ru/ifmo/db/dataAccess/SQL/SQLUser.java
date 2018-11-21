package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DTO.UserDTO;
import ru.ifmo.db.dataAccess.UserDAO;

public class SQLUser implements UserDAO {
    @Override
    public UserDTO get(String userName) {
        return null;
    }

    @Override
    public int add(UserDTO userDTO) {
        return 0;
    }

    @Override
    public void update(int id, UserDTO userDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
