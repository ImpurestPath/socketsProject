package ru.ifmo.db.dataAccess.DAO;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmDTO;

import java.util.List;

public interface FilmDAO extends DAO<FilmDTO>, getAllDAO<FilmDTO> {

    List<FilmCostDTO> getAllCosts(int idFilm);

    FilmCostDTO getCost(int idCost);

    List<Integer> getActors(int idFilm);

    List<Integer> getGenres(int idFilm);

    List<Integer> getSubscriptions(int idFilm);

    void addGenre(int idFilm, int idGenre);

    void addActor(int idFilm, int idActor);

    int addCost(FilmCostDTO dto);

    void updateCost(int id, FilmCostDTO dto);

    void deleteGenre(int idFilm, int idGenre);

    void deleteActor(int idFilm, int idActor);

    void deleteCost(int idCost);
}
