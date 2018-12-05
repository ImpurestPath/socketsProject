package ru.ifmo.db.dataAccess.DAO;

import java.util.List;

public interface getAllDAO<T> {
    List<T> getAll();
}
