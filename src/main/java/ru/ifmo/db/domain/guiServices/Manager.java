package ru.ifmo.db.domain.guiServices;

public interface Manager<T> {
    T getById(int id);
    int add(T obj);
    void update(T obj);
    void delete(int id);
}
