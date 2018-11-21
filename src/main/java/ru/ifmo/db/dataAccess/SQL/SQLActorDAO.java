package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.ActorDAO;
import ru.ifmo.db.dataAccess.DTO.ActorDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLActorDAO implements ActorDAO {
    private Connection connection;

    public SQLActorDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ActorDTO> getAll(int idFilm) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT idActor,Name FROM Actor WHERE idActor IN (SELECT idActor FROM [Film Actor] WHERE idFilm = ?)")){
            preparedStatement.setInt(1,idFilm);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ActorDTO> actors = new ArrayList<>();
            while (resultSet.next()){
                actors.add(new ActorDTO(resultSet.getInt(1),resultSet.getString(2)));
            }

            return actors;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ActorDTO get(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT Name FROM Actor WHERE idActor = ?")){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new ActorDTO(id,resultSet.getString(1));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int add(ActorDTO actorDTO) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Actor(Name) VALUES (?)")){
            preparedStatement.setString(1,actorDTO.getName());
            preparedStatement.execute();
            return connection.createStatement().executeQuery("SELECT last_insert_rowid()").getInt(1);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public void update(int id, ActorDTO actorDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Actor SET Name = ? WHERE idActor = ?")) {
            preparedStatement.setString(1,actorDTO.getName());
            preparedStatement.setInt(2,actorDTO.getId());
            preparedStatement.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Actor WHERE idActor = ?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
