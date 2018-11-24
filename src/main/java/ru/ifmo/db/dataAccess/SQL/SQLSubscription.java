package ru.ifmo.db.dataAccess.SQL;


import ru.ifmo.db.dataAccess.SubscriptionCostDAO;
import ru.ifmo.db.dataAccess.SubscriptionDAO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLSubscription implements SubscriptionDAO {
    private Connection connection;
    private SubscriptionCostDAO subscriptionCostDAO;

    public SQLSubscription(Connection connection) {
        this.connection = connection;
        this.subscriptionCostDAO = new SQLSubscriptionCost(connection);
    }

    public List<SubscriptionDTO> getAll() {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT idSubscription,Name FROM Subscription")){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<SubscriptionDTO> subscriptions = new ArrayList<>();
            while (resultSet.next()){
                subscriptions.add(new SubscriptionDTO(resultSet.getInt(1),resultSet.getString(2)));
            }
            return subscriptions;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public SubscriptionDTO getById(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT Name FROM Subscription WHERE idSubscription = ?")){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new SubscriptionDTO(id,resultSet.getString(1));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SubscriptionCostDTO> getAllCosts(int idSubscription) {
        return subscriptionCostDAO.getAll(idSubscription);
    }

    @Override
    public SubscriptionCostDTO getCost(int idCost) {
        return subscriptionCostDAO.getById(idCost);
    }

    @Override
    public List<Integer> getFilms(int idSubscription) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT idFilm FROM [Film By Subscription] WHERE idSubscription = ?")) {
            preparedStatement.setInt(1, idSubscription);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> integers = new ArrayList<>();
            while (resultSet.next()){
                integers.add(resultSet.getInt(1));
            }
            return integers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add(SubscriptionDTO subscriptionDTO) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Subscription(Name) VALUES (?)")){
            preparedStatement.setString(1,subscriptionDTO.getName());
            preparedStatement.execute();
            return connection.createStatement().executeQuery("SELECT last_insert_rowid()").getInt(1);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void addFilm(int idSubscription, int idFilm) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO [Film By Subscription](idSubscription, idFilm) VALUES (?,?)")) {
            preparedStatement.setInt(1, idSubscription);
            preparedStatement.setInt(2, idFilm);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addCost(SubscriptionCostDTO dto) {
        return subscriptionCostDAO.add(dto);
    }

    public void update(int id, SubscriptionDTO subscriptionDTO) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Subscription SET Name = ? WHERE idSubscription = ?")) {
            preparedStatement.setString(1,subscriptionDTO.getName());
            preparedStatement.setInt(2,subscriptionDTO.getId());
            preparedStatement.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCost(int id, SubscriptionCostDTO dto) {
        subscriptionCostDAO.update(id, dto);
    }

    public void delete(int id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Subscription WHERE idSubscription = ?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilm(int idSubscription, int idFilm) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM [Film By Subscription]  WHERE idFilm = ? AND idSubscription = ?")) {
            preparedStatement.setInt(1, idFilm);
            preparedStatement.setInt(2, idSubscription);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCost(int id) {
        subscriptionCostDAO.delete(id);
    }
}
