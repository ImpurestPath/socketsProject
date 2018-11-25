package ru.ifmo.db.gui;

public interface Manager<T> {
    T getById(int id);
    int add(T obj);
    void update(T obj);
    void delete(T obj);
}
