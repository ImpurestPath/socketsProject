package ru.ifmo.db;

import ru.ifmo.db.DTO.FilmDTO;

import java.util.List;

public interface FilmDAO {
    List<FilmDTO> getAll();
    FilmDTO get(int id);
    int add(FilmDTO filmDTO);
    void update(int id, FilmDTO filmDTO);
    void delete(int id);
}
