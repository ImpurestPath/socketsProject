package ru.ifmo.db.dataAccess.DAO;

import java.util.List;

public interface DAO<T> {
    T getById(int id);

    int add(T dto);

    void update(int id, T dto);

    void delete(int id);
}
