package ru.ifmo.db.dataAccess.SQL;


import ru.ifmo.db.dataAccess.DAO.GenreDAO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.GenreDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLGenre implements GenreDAO {
    private Connection connection;

    SQLGenre(Connection connection) {
        this.connection = connection;
    }

    public List<GenreDTO> getAllById(int idFilm) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT idGenre,Name FROM Genre WHERE idGenre IN " +
                            "(SELECT idGenre FROM [Film Genre] WHERE idFilm = ?)")){
            preparedStatement.setInt(1,idFilm);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GenreDTO> genres = new ArrayList<>();
            while (resultSet.next()){
                genres.add(new GenreDTO(resultSet.getInt(1),resultSet.getString(2)));
            }
            return genres;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public GenreDTO getById(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT Name FROM Genre WHERE idGenre = ?")){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new GenreDTO(id,resultSet.getString(1));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int add(GenreDTO genreDTO) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Genre(Name) VALUES (?)")){
            preparedStatement.setString(1,genreDTO.getName());
            preparedStatement.execute();
            return connection.createStatement().executeQuery("SELECT SCOPE_IDENTITY()").getInt(1);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public void update(int id, GenreDTO genreDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Genre SET Name = ? WHERE idGenre = ?")) {
            preparedStatement.setString(1,genreDTO.getName());
            preparedStatement.setInt(2,genreDTO.getId());
            preparedStatement.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Genre WHERE idGenre = ?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
