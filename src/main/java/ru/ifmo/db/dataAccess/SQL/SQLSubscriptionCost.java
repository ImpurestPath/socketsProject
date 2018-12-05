package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DAO.SubscriptionCostDAO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLSubscriptionCost implements SubscriptionCostDAO {
    private Connection connection;

    SQLSubscriptionCost(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<SubscriptionCostDTO> getAllById(int idSubscription) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT idSubscriptionCost,Duration,Cost " +
                            "FROM [Subscription Cost] " +
                            "WHERE idSubscription = ?")){
            preparedStatement.setInt(1,idSubscription);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<SubscriptionCostDTO> subscriptionCosts = new ArrayList<>();
            while (resultSet.next()){
                subscriptionCosts.add(new SubscriptionCostDTO(
                        resultSet.getInt(1),
                        idSubscription,
                        resultSet.getInt(2),
                        resultSet.getDouble(3)));
            }
            return subscriptionCosts;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SubscriptionCostDTO getById(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT idSubscription,Duration,Cost " +
                            "FROM [Subscription Cost] " +
                            "WHERE idSubscriptionCost = ?")){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new SubscriptionCostDTO(
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
    public int add(SubscriptionCostDTO subscriptionCostDTO) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO [Subscription Cost](idSubscription, Duration, Cost) VALUES (?,?,?)")){
            preparedStatement.setInt(1,subscriptionCostDTO.getIdSubscription());
            preparedStatement.setInt(2,subscriptionCostDTO.getDuration());
            preparedStatement.setDouble(3,subscriptionCostDTO.getCost());
            preparedStatement.execute();
            return connection.createStatement().executeQuery("SELECT SCOPE_IDENTITY()").getInt(1);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void update(int id, SubscriptionCostDTO subscriptionCostDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE [Subscription Cost] " +
                             "SET idSubscription = ?, Duration = ?, Cost = ? " +
                             "WHERE idSubscriptionCost = ?")) {
            preparedStatement.setInt(1,subscriptionCostDTO.getIdSubscription());
            preparedStatement.setInt(2,subscriptionCostDTO.getDuration());
            preparedStatement.setDouble(3,subscriptionCostDTO.getCost());
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
                    connection.prepareStatement("DELETE FROM [Subscription Cost] WHERE idSubscriptionCost = ?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
