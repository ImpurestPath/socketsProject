package ru.ifmo.db.dataAccess;

import java.util.List;

public interface getAllDAO<T> {
    List<T> getAll();
}
