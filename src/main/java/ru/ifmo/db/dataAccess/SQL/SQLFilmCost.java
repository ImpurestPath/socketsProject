package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DTO.FilmCostDTO;
import ru.ifmo.db.dataAccess.FilmCostDAO;

import java.util.List;

public class SQLFilmCost implements FilmCostDAO {
    @Override
    public List<FilmCostDTO> getAll(int idFilm) {
        return null;
    }

    @Override
    public FilmCostDTO get(int id) {
        return null;
    }

    @Override
    public int add(FilmCostDTO filmCostDTO) {
        return 0;
    }

    @Override
    public void update(int id, FilmCostDTO filmCostDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
