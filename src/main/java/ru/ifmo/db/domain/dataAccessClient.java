package ru.ifmo.db.domain;

import ru.ifmo.db.dataAccess.DTO.FilmDTO;
import ru.ifmo.db.domain.dataAccessDTO.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import ru.ifmo.db.domain.DataAccessCommands;

import static ru.ifmo.db.domain.DataAccessCommands.*;

public class dataAccessClient {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    dataAccessClient() {
        int serverPort = 3567; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    void sendTwoInt(DataAccessCommands command, int i1,int i2){
        try {
            out.writeObject(command);
            out.flush();
            out.writeInt(i1);
            out.flush();
            out.writeInt(i2);
            out.flush();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    <T extends Serializable> void sendIntAndObject(DataAccessCommands command,)
    <T extends Serializable> int sendObject(DataAccessCommands command, T object){
        try {
            out.writeObject(command);
            out.flush();
            out.writeObject(object);
            out.flush();
            int temp = in.readInt();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    int addFilm(FilmDTO dto) {
        return sendObject(ADD_FILM, dto);
    }

    void addFilmGenre(int idFilm, int idGenre) {
        sendTwoInt(ADD_FILM_GENRE, idFilm,idGenre);
    }

    int addFilmCost(FilmCostDTO dto) {
        return sendObject(ADD_FILM_COST, dto);
    }

    void addFilmActor(int idFilm, int idActor) {
        sendTwoInt(ADD_FILM_ACTOR,idFilm,idActor);
    }

    int addActor(ActorDTO dto) {
        return sendObject(ADD_ACTOR, dto);
    }

    int addGenre(GenreDTO dto) {
        return sendObject(ADD_GENRE,dto);
    }

    int addUser(UserDTO dto) {
        return  sendObject(ADD_USER,dto);
    }

    void addUserSubscription(int idUser, int idSubscriptionCost) {
        sendTwoInt(ADD_USER_SUBSCRIPTION,idUser,idSubscriptionCost);
    }

    void addUserFilm(int idUser, int idFilmCost) {
        sendTwoInt(ADD_USER_FILM,idUser,idFilmCost);
    }

    int addSubscription(SubscriptionDTO dto) {
        return sendObject(ADD_SUBSCRIPTION,dto);
    }

    int addSubscriptionCost(SubscriptionCostDTO dto) {
        return sendObject(ADD_SUBSCRIPTION_COST,dto);
    }

    void addSubscriptionFilm(int idSubscription, int idFilm) {
        sendTwoInt(ADD_SUBSCRIPTION_FILM, idSubscription,idFilm);
    }

    void updateFilm(int id, FilmDTO filmDTO) {

    }

    void updateFilmCost(int id, FilmCostDTO dto) {
    }

    void updateActor(int id, ActorDTO dto) {
    }

    void updateGenre(int id, GenreDTO dto) {
    }

    void updateUser(int id, UserDTO dto) {
    }

    void updateSubscription(int id, SubscriptionDTO dto) {
    }

    void updateSubscriptionCost(int id, SubscriptionCostDTO dto) {
    }

    void deleteFilm(int id) {
    }

    void deleteFilmGenre(int idFilm, int idGenre) {
    }

    void deleteFilmCost(int id) {
    }

    void deleteFilmActor(int idFilm, int idActor) {
    }

    void deleteActor(int id) {
    }

    void deleteGenre(int id) {
    }

    void deleteUser(int id) {
    }

    void deleteUserSubscription(int idUser, int idSubscription) {
    }

    void deleteUserFilm(int idUser, int idFilm) {
    }

    void deleteSubscription(int id) {
    }

    void deleteSubscriptionCost(int id) {
    }

    void deleteSubscriptionFilm(int idSubscription, int idFilm) {
    }

    List<FilmDTO> getAllFilms() {
    }

    FilmDTO getFilm(int id) {
    }

    List<FilmCostDTO> getAllFilmCosts(int idFilm) {
    }

    FilmCostDTO getFilmCost(int idFilmCost) {
    }

    List<Integer> getFilmSubscriptions(int idFilm) {
    }

    List<Integer> getFilmActors(int idFilm) {
    }

    List<Integer> getFilmGenres(int idFilm) {
    }

    ActorDTO getActor(int id) {
    }

    GenreDTO getGenre(int id) {
    }

    List<SubscriptionDTO> getAllSubscriptions() {
    }

    SubscriptionDTO getSubscription(int id) {
    }

    List<SubscriptionCostDTO> getAllSubscriptionCosts(int idSubscription) {
    }

    SubscriptionCostDTO getSubscriptionCost(int idSubscriptionCost) {
    }

    List<Integer> getSubscriptionFilms(int idSubscription) {
    }

    UserDTO getUser(String userName){}

}
