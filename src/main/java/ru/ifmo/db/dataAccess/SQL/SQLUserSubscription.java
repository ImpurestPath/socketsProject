package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DAO.UserPurchaseDAO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SQLUserSubscription implements UserPurchaseDAO {
    private Connection connection;

    public SQLUserSubscription(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UserPurchaseDTO> getById( int idUser,int idTypeOfPurchase) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT StartDate, EndDate  " +
                             "FROM [Purchased Subscriptions] " +
                             "WHERE idUser = ? AND idSubscription = ?")) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idTypeOfPurchase);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserPurchaseDTO> subscriptionCosts = new ArrayList<>();
            while (resultSet.next()) {
                subscriptionCosts.add(new UserPurchaseDTO(
                        idTypeOfPurchase,
                        idUser,
                        resultSet.getTimestamp(1),
                        resultSet.getTimestamp(2)));
            }
            return subscriptionCosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(int idCost, int idUser) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "EXEC buySubscription ?,?")) {
            preparedStatement.setInt(1, idCost);
            preparedStatement.setInt(2, idUser);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserPurchaseDTO dto) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM [Purchased Subscriptions] WHERE idUser = ? AND idSubscription = ? AND StartDate = ?")) {
            preparedStatement.setInt(1, dto.getIdUser());
            preparedStatement.setInt(2, dto.getIdPurchase());
            preparedStatement.setTimestamp(3, new Timestamp(dto.getStart().getTime()));
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserPurchaseDTO> getAllById(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT idSubscription, StartDate, EndDate  " +
                             "FROM [Purchased Subscriptions] " +
                             "WHERE idUser = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserPurchaseDTO> subscriptionCosts = new ArrayList<>();
            while (resultSet.next()) {
                subscriptionCosts.add(new UserPurchaseDTO(
                        resultSet.getInt(1),
                        id,
                        resultSet.getTimestamp(2),
                        resultSet.getTimestamp(3)));
            }
            return subscriptionCosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
