package ru.ifmo.db.dataAccess;



import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;

import java.util.List;

public interface FilmCostDAO {
    List<FilmCostDTO> getAll(int idFilm);
    FilmCostDTO getById(int id);
    int add(FilmCostDTO filmCostDTO);
    void update(int id,FilmCostDTO filmCostDTO);
    void delete(int id);
}
