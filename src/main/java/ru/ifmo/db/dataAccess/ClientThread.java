package ru.ifmo.db.dataAccess;

import ru.ifmo.db.dataAccess.DTO.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private ConnectionDAO connection;
    ClientThread(Socket socket, ConnectionDAO connection){
        this.socket = socket;
        this.connection = connection;
    }
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            DataAccessCommands command;
            int id;
            int idFilm;
            int idActor;
            int idGenre;
            int idSubscription;
            int idUser;
            FilmDTO filmDTO;
            FilmCostDTO filmCostDTO;
            ActorDTO actorDTO;
            GenreDTO genreDTO;
            UserDTO userDTO;
            SubscriptionDTO subscriptionDTO;
            SubscriptionCostDTO subscriptionCostDTO;
            String username;
            while (true) {
                command = (DataAccessCommands) in.readObject(); // ожидаем пока клиент пришлет строку текста.
                out.writeObject(DataAccessCommands.FINISHED);
                switch (command) {
                    case ADD_FILM:
                        filmDTO = (FilmDTO) in.readObject();
                        out.writeInt(connection.addFilm(filmDTO));
                        out.flush();
                        break;
                    case ADD_FILM_GENRE:
                        idFilm = in.readInt();
                        idGenre = in.readInt();
                        connection.addFilmGenre(idFilm, idGenre);
                        break;
                    case ADD_FILM_COST:
                        filmCostDTO = (FilmCostDTO) in.readObject();
                        out.writeInt(connection.addFilmCost(filmCostDTO));
                        out.flush();
                        break;
                    case ADD_FILM_ACTOR:
                        idFilm = in.readInt();
                        idActor = in.readInt();
                        connection.addFilmActor(idFilm, idActor);
                        break;
                    case ADD_ACTOR:
                        actorDTO = (ActorDTO) in.readObject();
                        out.writeInt(connection.addActor(actorDTO));
                        out.flush();
                        break;
                    case ADD_GENRE:
                        genreDTO = (GenreDTO) in.readObject();
                        out.writeInt(connection.addGenre(genreDTO));
                        out.flush();
                        break;
                    case ADD_USER:
                        userDTO = (UserDTO) in.readObject();
                        out.writeInt(connection.addUser(userDTO));
                        out.flush();
                        break;
                    case ADD_USER_SUBSCRIPTION:
                        idUser = in.readInt();
                        idSubscription = in.readInt();
                        connection.addUserSubscription(idUser, idSubscription);
                        break;
                    case ADD_USER_FILM:
                        idUser = in.readInt();
                        idFilm = in.readInt();
                        connection.addUserFilm(idUser, idFilm);
                        break;
                    case ADD_SUBSCRIPTION:
                        subscriptionDTO = (SubscriptionDTO) in.readObject();
                        out.writeInt(connection.addSubscription(subscriptionDTO));
                        out.flush();
                        break;
                    case ADD_SUBSCRIPTION_COST:
                        subscriptionCostDTO = (SubscriptionCostDTO) in.readObject();
                        out.writeInt(connection.addSubscriptionCost(subscriptionCostDTO));
                        out.flush();
                        break;
                    case ADD_SUBSCRIPTION_FILM:
                        idSubscription = in.readInt();
                        idFilm = in.readInt();
                        connection.addSubscriptionFilm(idSubscription, idFilm);
                        break;
                    case UPDATE_FILM:
                        id = in.readInt();
                        filmDTO = (FilmDTO) in.readObject();
                        connection.updateFilm(id, filmDTO);
                        break;
                    case UPDATE_FILM_COST:
                        id = in.readInt();
                        filmCostDTO = (FilmCostDTO) in.readObject();
                        connection.updateFilmCost(id, filmCostDTO);
                        break;
                    case UPDATE_ACTOR:
                        id = in.readInt();
                        actorDTO = (ActorDTO) in.readObject();
                        connection.updateActor(id, actorDTO);
                        break;
                    case UPDATE_GENRE:
                        id = in.readInt();
                        genreDTO = (GenreDTO) in.readObject();
                        connection.updateGenre(id, genreDTO);
                        break;
                    case UPDATE_USER:
                        id = in.readInt();
                        userDTO = (UserDTO) in.readObject();
                        connection.updateUser(id, userDTO);
                        break;
                    case UPDATE_SUBSCRIPTION:
                        id = in.readInt();
                        subscriptionDTO = (SubscriptionDTO) in.readObject();
                        connection.updateSubscription(id, subscriptionDTO);
                        break;
                    case UPDATE_SUBSCRIPTION_COST:
                        id = in.readInt();
                        subscriptionCostDTO = (SubscriptionCostDTO) in.readObject();
                        connection.updateSubscriptionCost(id, subscriptionCostDTO);
                        break;
                    case DELETE_FILM:
                        id = in.readInt();
                        connection.deleteFilm(id);
                        break;
                    case DELETE_FILM_GENRE:
                        idFilm = in.readInt();
                        idGenre = in.readInt();
                        connection.deleteFilmGenre(idFilm, idGenre);
                        break;
                    case DELETE_FILM_COST:
                        id = in.readInt();
                        connection.deleteFilmCost(id);
                        break;
                    case DELETE_FILM_ACTOR:
                        idFilm = in.readInt();
                        idActor = in.readInt();
                        connection.deleteFilmActor(idFilm, idActor);
                        break;
                    case DELETE_ACTOR:
                        id = in.readInt();
                        connection.deleteActor(id);
                        break;
                    case DELETE_GENRE:
                        id = in.readInt();
                        connection.deleteGenre(id);
                        break;
                    case DELETE_USER:
                        id = in.readInt();
                        connection.deleteUser(id);
                        break;
                    case DELETE_USER_SUBSCRIPTION:
                        idUser = in.readInt();
                        idSubscription = in.readInt();
                        connection.deleteUserSubscription(idUser, idSubscription);
                        break;
                    case DELETE_USER_FILM:
                        idUser = in.readInt();
                        idFilm = in.readInt();
                        connection.deleteUserFilm(idUser, idFilm);
                        break;
                    case DELETE_SUBSCRIPTION:
                        id = in.readInt();
                        connection.deleteSubscription(id);
                        break;
                    case DELETE_SUBSCRIPTION_COST:
                        id = in.readInt();
                        connection.deleteSubscriptionCost(id);
                        break;
                    case DELETE_SUBSCRIPTION_FILM:
                        idSubscription = in.readInt();
                        idFilm = in.readInt();
                        connection.deleteSubscriptionFilm(idSubscription, idFilm);
                        break;
                    case FINISHED:
                        break;
                    case GET_ALL_FILMS:
                        out.writeObject(connection.getAllFilms());
                        break;
                    case GET_FILM:
                        id = in.readInt();
                        out.writeObject(connection.getFilm(id));
                        break;
                    case GET_ALL_FILM_COSTS:
                        id = in.readInt();
                        out.writeObject(connection.getAllFilmCosts(id));
                        break;
                    case GET_FILM_COST:
                        id = in.readInt();
                        out.writeObject(connection.getFilmCost(id));
                        break;
                    case GET_FILM_SUBSCRIPTIONS:
                        id = in.readInt();
                        out.writeObject(connection.getFilmSubscriptions(id));
                        break;
                    case GET_FILM_ACTORS:
                        id = in.readInt();
                        out.writeObject(connection.getFilmActors(id));
                        break;
                    case GET_FILM_GENRES:
                        id = in.readInt();
                        out.writeObject(connection.getFilmGenres(id));
                        break;
                    case GET_ACTOR:
                        id = in.readInt();
                        out.writeObject(connection.getActor(id));
                        break;
                    case GET_GENRE:
                        id = in.readInt();
                        out.writeObject(connection.getGenre(id));
                        break;
                    case GET_ALL_SUBSCRIPTIONS:
                        out.writeObject(connection.getAllSubscriptions());
                        break;
                    case GET_SUBSCRIPTION:
                        id = in.readInt();
                        out.writeObject(connection.getSubscription(id));
                        break;
                    case GET_ALL_SUBSCRIPTION_COSTS:
                        id = in.readInt();
                        out.writeObject(connection.getAllSubscriptionCosts(id));
                        break;
                    case GET_SUBSCRIPTION_COST:
                        id = in.readInt();
                        out.writeObject(connection.getSubscriptionCost(id));
                        break;
                    case GET_SUBSCRIPTION_FILMS:
                        id = in.readInt();
                        out.writeObject(connection.getSubscriptionFilms(id));
                        break;
                    case GET_USER:
                        username = in.readUTF();
                        out.writeObject(connection.getUser(username));
                        break;
                    case CLOSE_CONNECTION:
                        socket.close();
                        in.close();
                        out.close();
                }
                out.writeObject(DataAccessCommands.FINISHED);
                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("Waiting for the next line...");
            }
        } catch (Exception e){
            e.printStackTrace();
            //if (out != null) out.writeObject(Command.ERROR);

        }
    }
}
