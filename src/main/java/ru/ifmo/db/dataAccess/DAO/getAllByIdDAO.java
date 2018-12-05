package ru.ifmo.db.dataAccess.DAO;

import java.util.List;

public interface getAllByIdDAO<T> {
    List<T> getAllById(int id);
}
