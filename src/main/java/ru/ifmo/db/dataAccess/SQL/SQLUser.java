package ru.ifmo.db.dataAccess.SQL;


import ru.ifmo.db.dataAccess.UserDAO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLUser implements UserDAO {
    private Connection connection;

    public SQLUser(Connection connection) {
        this.connection = connection;
    }

    public UserDTO get(String userName) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT idUser, [Name], Balance FROM [User] WHERE [Name] = ?")) {
            preparedStatement.setString(1, '\'' + userName + '\'');
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new UserDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add(UserDTO userDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO [User](Name,Balance) VALUES (?,?)")) {
            preparedStatement.setString(1, userDTO.getUserName());
            preparedStatement.setDouble(2, userDTO.getBalance());
            preparedStatement.execute();
            return connection.createStatement().executeQuery("SELECT SCOPE_IDENTITY()").getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void addFilm(int idUser, int idFilm) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO [Purchased Movies](idUser,idFilm) VALUES (?,?)")) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idFilm);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void addSubscription(int idUser, int idSubscription) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO [Purchased Subscriptions](idUser,idSubscription) VALUES (?,?)")) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idSubscription);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void update(int id, UserDTO userDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE [User] SET Name = ?, Balance = ? WHERE idUser = ?")) {
            preparedStatement.setString(1, userDTO.getUserName());
            preparedStatement.setDouble(2, userDTO.getBalance());
            preparedStatement.setInt(3, userDTO.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM [User] WHERE idUser = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilm(int idUser, int idFilm) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM [Purchased Movies] WHERE idUser = ? AND idFilm = ?")) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idFilm);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubscription(int idUser, int idSubscription) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM [Purchased Subscriptions] WHERE idUser = ? AND idSubscription = ?")) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idSubscription);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
