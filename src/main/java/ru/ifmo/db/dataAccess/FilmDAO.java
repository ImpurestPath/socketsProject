package ru.ifmo.db.dataAccess;

import ru.ifmo.db.dataAccess.DTO.FilmCostDTO;
import ru.ifmo.db.dataAccess.DTO.FilmDTO;

import java.util.List;

public interface FilmDAO {
    List<FilmDTO> getAll();

    FilmDTO get(int id);

    int add(FilmDTO filmDTO);

    void addGenre(int idFilm, int idGenre);

    void addActor(int idFilm, int idActor);

    int addCost(FilmCostDTO dto);

    void update(int id, FilmDTO filmDTO);

    void updateCost(int id, FilmCostDTO dto);

    void delete(int id);

    void deleteGenre(int idFilm, int idGenre);

    void deleteActor(int idFilm, int idActor);

    void deleteCost(int idCost);
}
