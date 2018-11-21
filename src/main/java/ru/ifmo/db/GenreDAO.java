package ru.ifmo.db;

import ru.ifmo.db.DTO.GenreDTO;

import java.util.List;

public interface GenreDAO {
    List<GenreDTO> getAll(int idFilm);
    GenreDTO get(int id);
    int add(GenreDTO genreDTO);
    void update(int id, GenreDTO genreDTO);
    void delete(int id);
}
