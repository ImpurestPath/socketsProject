package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DTO.FilmCostDTO;
import ru.ifmo.db.dataAccess.FilmCostDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLFilmCost implements FilmCostDAO {
    private Connection connection;

    public SQLFilmCost(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<FilmCostDTO> getAll(int idFilm) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT idFilmCost,Duration,Cost FROM [Film Cost] WHERE idFilm = ?")){
            preparedStatement.setInt(1,idFilm);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<FilmCostDTO> filmCosts = new ArrayList<>();
            while (resultSet.next()){
                filmCosts.add(new FilmCostDTO(
                        resultSet.getInt(1),
                        idFilm,
                        resultSet.getInt(2),
                        resultSet.getDouble(3)));
            }
            return filmCosts;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FilmCostDTO get(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT idFilm,Duration,Cost FROM [Film Cost] WHERE idFilmCost = ?")){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new FilmCostDTO(
                    id,
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int add(FilmCostDTO filmCostDTO) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO [Film Cost](idFilm, Duration, Cost) VALUES (?,?,?)")){
            preparedStatement.setInt(1,filmCostDTO.getIdFilm());
            preparedStatement.setInt(2,filmCostDTO.getDuration());
            preparedStatement.setDouble(3,filmCostDTO.getCost());
            preparedStatement.execute();
            return connection.createStatement().executeQuery("SELECT last_insert_rowid()").getInt(1);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void update(int id, FilmCostDTO filmCostDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE [Film Cost] " +
                             "SET idFilm = ?, Duration = ?, Cost = ? " +
                             "WHERE idFilmCost = ?")) {
            preparedStatement.setInt(1,filmCostDTO.getIdFilm());
            preparedStatement.setInt(2,filmCostDTO.getDuration());
            preparedStatement.setDouble(3,filmCostDTO.getCost());
            preparedStatement.setInt(4,id);
            preparedStatement.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM [Film Cost] WHERE idFilmCost = ?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
