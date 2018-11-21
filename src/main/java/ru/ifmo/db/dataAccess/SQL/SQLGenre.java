package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DTO.GenreDTO;
import ru.ifmo.db.dataAccess.GenreDAO;

import java.util.List;

public class SQLGenre implements GenreDAO {
    @Override
    public List<GenreDTO> getAll(int idFilm) {
        return null;
    }

    @Override
    public GenreDTO get(int id) {
        return null;
    }

    @Override
    public int add(GenreDTO genreDTO) {
        return 0;
    }

    @Override
    public void update(int id, GenreDTO genreDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
