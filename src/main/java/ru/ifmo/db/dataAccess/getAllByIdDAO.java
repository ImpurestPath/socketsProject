package ru.ifmo.db.dataAccess;

import java.util.List;

public interface getAllByIdDAO<T> {
    List<T> getAllById(int id);
}
