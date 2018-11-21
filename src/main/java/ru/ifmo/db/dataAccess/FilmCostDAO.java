package ru.ifmo.db.dataAccess;

import ru.ifmo.db.dataAccess.DTO.FilmCostDTO;

import java.util.List;

public interface FilmCostDAO {
    List<FilmCostDTO> getAll(int idFilm);
    FilmCostDTO get(int id);
    int add(FilmCostDTO filmCostDTO);
    void update(int id,FilmCostDTO filmCostDTO);
    void delete(int id);
}
