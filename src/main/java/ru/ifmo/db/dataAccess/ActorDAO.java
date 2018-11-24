package ru.ifmo.db.dataAccess;



import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.ActorDTO;

import java.util.List;

public interface ActorDAO {
    List<ActorDTO> getAll(int idFilm);
    ActorDTO get(int id);
    int add(ActorDTO actorDTO);
    void update(int id, ActorDTO actorDAO);
    void delete(int id);
}
