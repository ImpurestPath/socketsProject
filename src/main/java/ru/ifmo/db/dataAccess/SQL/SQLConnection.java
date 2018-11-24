package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.*;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class SQLConnection implements ConnectionDAO {
    private FilmDAO filmDAO;
    private ActorDAO actorDAO;
    //private FilmCostDAO filmCostDAO;
    private GenreDAO genreDAO;
    private SubscriptionDAO subscriptionDAO;
    //private SubscriptionCostDAO subscriptionCostDAO;
    private UserDAO userDAO;

    public SQLConnection() {
        String connectionUrl = "jdbc:sqlserver://192.168.43.2:1433;databaseName=OnlineCinema;user=user2;password=user";
        try {
            // Load SQL Server JDBC driver and establish connection.
            //System.out.print("Connecting to SQL Server ... ");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Connection connection = DriverManager.getConnection(connectionUrl);
            filmDAO = new SQLFilm(connection);
            actorDAO = new SQLActorDAO(connection);
            //filmCostDAO = new SQLFilmCost(connection);
            genreDAO = new SQLGenre(connection);
            subscriptionDAO = new SQLSubscription(connection);
            //subscriptionCostDAO = new SQLSubscriptionCost(connection);
            userDAO = new SQLUser(connection);
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }


    @Override
    public int addFilm(FilmDTO dto) {
        return filmDAO.add(dto);
    }

    @Override
    public void addFilmGenre(int idFilm, int idGenre) {
        filmDAO.addGenre(idFilm, idGenre);
    }

    @Override
    public int addFilmCost(FilmCostDTO dto) {
        return filmDAO.addCost(dto);
    }

    @Override
    public void addFilmActor(int idFilm, int idActor) {
        filmDAO.addActor(idFilm, idActor);
    }

    @Override
    public int addActor(ActorDTO dto) {
        return actorDAO.add(dto);
    }

    @Override
    public int addGenre(GenreDTO dto) {
        return genreDAO.add(dto);
    }

    @Override
    public int addUser(UserDTO dto) {
        return userDAO.add(dto);
    }

    @Override
    public void addUserSubscription(int idUser, int idSubscriptionCost) {
        userDAO.addSubscription(idUser, idSubscriptionCost);
    }

    @Override
    public void addUserFilm(int idUser, int idFilmCost) {
        userDAO.addFilm(idUser, idFilmCost);
    }

    @Override
    public int addSubscription(SubscriptionDTO dto) {
        return subscriptionDAO.add(dto);
    }

    @Override
    public int addSubscriptionCost(SubscriptionCostDTO dto) {
        return subscriptionDAO.addCost(dto);
    }

    @Override
    public void addSubscriptionFilm(int idSubscription, int idFilm) {
        subscriptionDAO.addFilm(idSubscription, idFilm);
    }

    @Override
    public void updateFilm(int id, FilmDTO filmDTO) {
        filmDAO.update(id, filmDTO);
    }

    @Override
    public void updateFilmCost(int id, FilmCostDTO dto) {
        filmDAO.updateCost(id, dto);
    }

    @Override
    public void updateActor(int id, ActorDTO dto) {
        actorDAO.update(id, dto);
    }

    @Override
    public void updateGenre(int id, GenreDTO dto) {
        genreDAO.update(id, dto);
    }

    @Override
    public void updateUser(int id, UserDTO dto) {
        userDAO.update(id, dto);
    }

    @Override
    public void updateSubscription(int id, SubscriptionDTO dto) {
        subscriptionDAO.update(id, dto);
    }

    @Override
    public void updateSubscriptionCost(int id, SubscriptionCostDTO dto) {
        subscriptionDAO.updateCost(id, dto);
    }

    @Override
    public void deleteFilm(int id) {
        filmDAO.delete(id);
    }

    @Override
    public void deleteFilmGenre(int idFilm, int idGenre) {
        filmDAO.deleteGenre(idFilm, idGenre);
    }

    @Override
    public void deleteFilmCost(int id) {
        filmDAO.deleteCost(id);
    }

    @Override
    public void deleteFilmActor(int idFilm, int idActor) {
        filmDAO.deleteActor(idFilm, idActor);
    }

    @Override
    public void deleteActor(int id) {
        actorDAO.delete(id);
    }

    @Override
    public void deleteGenre(int id) {
        genreDAO.delete(id);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Override
    public void deleteUserSubscription(int idUser, int idSubscription) {
        userDAO.deleteSubscription(idUser, idSubscription);
    }

    @Override
    public void deleteUserFilm(int idUser, int idFilm) {
        userDAO.deleteFilm(idUser, idFilm);
    }

    @Override
    public void deleteSubscription(int id) {
        subscriptionDAO.delete(id);
    }

    @Override
    public void deleteSubscriptionCost(int id) {
        subscriptionDAO.deleteCost(id);
    }

    @Override
    public void deleteSubscriptionFilm(int idSubscription, int idFilm) {

    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmDAO.getAll();
    }

    @Override
    public FilmDTO getFilm(int id) {
        return filmDAO.getById(id);
    }

    @Override
    public List<FilmCostDTO> getAllFilmCosts(int idFilm) {
        return filmDAO.getAllCosts(idFilm);
    }

    @Override
    public FilmCostDTO getFilmCost(int idFilmCost) {
        return filmDAO.getCost(idFilmCost);
    }

    @Override
    public List<Integer> getFilmSubscriptions(int idFilm) {
        return filmDAO.getSubscriptions(idFilm);
    }

    @Override
    public List<Integer> getFilmActors(int idFilm) {
        return filmDAO.getActors(idFilm);
    }

    @Override
    public ActorDTO getActor(int id) {
        return actorDAO.get(id);
    }

    @Override
    public List<Integer> getFilmGenres(int idFilm) {
        return filmDAO.getGenres(idFilm);
    }

    @Override
    public GenreDTO getGenre(int id) {
        return genreDAO.getById(id);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionDAO.getAll();
    }

    @Override
    public SubscriptionDTO getSubscription(int id) {
        return subscriptionDAO.getById(id);
    }

    @Override
    public List<SubscriptionCostDTO> getAllSubscriptionCosts(int idSubscription) {
        return subscriptionDAO.getAllCosts(idSubscription);
    }

    @Override
    public SubscriptionCostDTO getSubscriptionCost(int idSubscriptionCost) {
        return subscriptionDAO.getCost(idSubscriptionCost);
    }

    @Override
    public List<Integer> getSubscriptionFilms(int idSubscription) {
        return subscriptionDAO.getFilms(idSubscription);
    }

    @Override
    public UserDTO getUser(String userName) {
        return userDAO.getByName(userName);
    }
}