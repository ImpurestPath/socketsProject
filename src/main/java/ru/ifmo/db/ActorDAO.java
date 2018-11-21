package ru.ifmo.db;

import ru.ifmo.db.DTO.ActorDTO;

import java.util.List;

public interface ActorDAO {
    List<ActorDTO> getAll(int idFilm);
    ActorDTO get(int id);
    int add(ActorDAO actorDAO);
    void update(int id, ActorDAO actorDAO);
    void delete(int id);
}
