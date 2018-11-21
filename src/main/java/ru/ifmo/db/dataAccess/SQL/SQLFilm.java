package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DTO.FilmDTO;
import ru.ifmo.db.dataAccess.FilmDAO;

import java.sql.Connection;
import java.util.List;

public class SQLFilm implements FilmDAO {
    private Connection connection;

    public SQLFilm(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<FilmDTO> getAll() {
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FilmDTO get(int id) {
        return null;
    }

    @Override
    public int add(FilmDTO filmDTO) {
        return 0;
    }

    @Override
    public void update(int id, FilmDTO filmDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
