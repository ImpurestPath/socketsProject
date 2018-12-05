package ru.ifmo.db.dataAccess.SQL;


import ru.ifmo.db.dataAccess.DAO.FilmCostDAO;
import ru.ifmo.db.dataAccess.DAO.FilmDAO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLFilm implements FilmDAO {
    private Connection connection;
    private FilmCostDAO filmCostDAO;

    public SQLFilm(Connection connection) {
        this.connection = connection;
        this.filmCostDAO = new SQLFilmCost(connection);
    }

    public List<FilmDTO> getAll() {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT idFilm,Name,Year,Reggiseur,Rating,[Positive reviews],[Neutral reviews],[Negative reviews] FROM Film")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<FilmDTO> films = new ArrayList<>();
            while (resultSet.next()) {
                films.add(new FilmDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getShort(3),
                        resultSet.getString(4),
                        resultSet.getShort(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8)));
            }
            return films;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public FilmDTO getById(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT idFilm,Name,Year,Reggiseur,Rating,[Positive reviews],[Neutral reviews],[Negative reviews] FROM Film WHERE idFilm = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new FilmDTO(
                    id,
                    resultSet.getString(2),
                    resultSet.getShort(3),
                    resultSet.getString(4),
                    resultSet.getShort(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FilmCostDTO> getAllCosts(int idFilm) {
        return filmCostDAO.getAllById(idFilm);
    }

    @Override
    public FilmCostDTO getCost(int idCost) {
        return filmCostDAO.getById(idCost);
    }

    @Override
    public List<Integer> getActors(int idFilm) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT idActor FROM [Film Actor] WHERE idFilm = ?")) {
            preparedStatement.setInt(1, idFilm);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getIntegersFromSelect(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Integer> getIntegersFromSelect(ResultSet resultSet) throws SQLException {
        List<Integer> integers = new ArrayList<>();
        while (resultSet.next()) {
            integers.add(resultSet.getInt(1));
        }
        return integers;
    }

    @Override
    public List<Integer> getGenres(int idFilm) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT idGenre FROM [Film Genre] WHERE idFilm = ?")) {
            preparedStatement.setInt(1, idFilm);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getIntegersFromSelect(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Integer> getSubscriptions(int idFilm) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT idSubscription FROM [Film By Subscription] WHERE idFilm = ?")) {
            preparedStatement.setInt(1, idFilm);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getIntegersFromSelect(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add(FilmDTO filmDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO Film(Name,Year,Reggiseur,Rating) VALUES (?,?,?,?)")) {
            preparedStatement.setString(1, filmDTO.getName());
            preparedStatement.setShort(2, filmDTO.getYear());
            preparedStatement.setString(3, filmDTO.getReggiseur());
            preparedStatement.setShort(4, filmDTO.getRating());
            preparedStatement.execute();
            return connection.createStatement().executeQuery("SELECT SCOPE_IDENTITY()").getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void addGenre(int idFilm, int idGenre) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO [Film Genre](idGenre, idFilm) VALUES (?,?)")) {
            preparedStatement.setInt(1, idGenre);
            preparedStatement.setInt(2, idFilm);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addActor(int idFilm, int idActor) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO [Film Actor](idActor, idFilm) VALUES (?,?)")) {
            preparedStatement.setInt(1, idActor);
            preparedStatement.setInt(2, idFilm);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addCost(FilmCostDTO dto) {
        return filmCostDAO.add(dto);
    }

    public void update(int id, FilmDTO filmDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Film " +
                             "SET Name = ?, Year = ?, Reggiseur = ?, Rating = ? " +
                             "WHERE idFilm = ?")) {
            preparedStatement.setString(1, filmDTO.getName());
            preparedStatement.setShort(2, filmDTO.getYear());
            preparedStatement.setString(3, filmDTO.getReggiseur());
            preparedStatement.setShort(4, filmDTO.getRating());
            preparedStatement.setInt(5, filmDTO.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCost(int id, FilmCostDTO dto) {
        filmCostDAO.update(id, dto);
    }

    public void delete(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM Film WHERE idFilm = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGenre(int idFilm, int idGenre) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM [Film Genre]  WHERE idFilm = ? AND idGenre = ?")) {
            preparedStatement.setInt(1, idFilm);
            preparedStatement.setInt(2, idGenre);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActor(int idFilm, int idActor) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM [Film Actor]  WHERE idFilm = ? AND idActor = ?")) {
            preparedStatement.setInt(1, idFilm);
            preparedStatement.setInt(2, idActor);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteCost(int idCost) {
        filmCostDAO.delete(idCost);
    }
}
