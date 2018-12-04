package ru.ifmo.db.domain.guiServices;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.guiServices.domainDTO.*;

import static ru.ifmo.db.domain.guiServices.Commands.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientThread extends Thread {
    private Socket socket;
    private Client client;
    private FilmManager filmManager;
    private UserManager userManager;
    private SubscriptionManager subscriptionManager;
    private ActorManager actorManager;
    private GenreManager genreManager;

    public ClientThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
        this.filmManager = new FilmManager(client);
        this.userManager = new UserManager(client);
        this.subscriptionManager = new SubscriptionManager(client);
        this.actorManager = new ActorManager(client);
        this.genreManager = new GenreManager(client);
    }

    public void run() {
        ObjectOutputStream out;
        ObjectInputStream in;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            Commands command;
            Film film;
            User user;
            Actor actor;
            Subscription subscription;
            Genre genre;
            Object obj;
            int id;
            int id2;
            while (true) {
                command = (Commands) in.readObject();
                switch (command) {
                    case ADD_FILM:
                        film = (Film) in.readObject();
                        out.writeInt(filmManager.add(film));
                        out.flush();

                        break;
                    case ADD_ACTOR:
                        actor = (Actor) in.readObject();
                        actorManager.add(actor);

                        break;
                    case ADD_GENRE:
                        genre = (Genre) in.readObject();
                        genreManager.add(genre);
                        break;
                    case ADD_USER:
                        user = (User) in.readObject();
                        userManager.add(user);

                        break;
                    case ADD_USER_SUBSCRIPTION:
                        id = in.readInt();
                        id2 = in.readInt();
                        userManager.buySubscription(id, id2);

                        break;
                    case ADD_USER_FILM:
                        id = in.readInt();
                        id2 = in.readInt();
                        userManager.buyFilm(id, id2);

                        break;
                    case ADD_SUBSCRIPTION:
                        subscription = (Subscription) in.readObject();
                        subscriptionManager.add(subscription);
                        break;
                    case UPDATE_FILM:
                        film = (Film) in.readObject();
                        out.flush();
                        filmManager.update(film);

                        break;
                    case UPDATE_ACTOR:
                        actor = (Actor) in.readObject();
                        actorManager.update(actor);
                        break;
                    case UPDATE_GENRE:
                        genre = (Genre) in.readObject();
                        genreManager.update(genre);
                        break;
                    case UPDATE_USER:
                        user = (User) in.readObject();
                        userManager.update(user);
                        break;
                    case UPDATE_SUBSCRIPTION:
                        subscription = (Subscription) in.readObject();
                        subscriptionManager.update(subscription);
                        break;
                    case DELETE_FILM:
                        id = in.readInt();
                        filmManager.delete(id);

                        break;
                    case DELETE_ACTOR:
                        id = in.readInt();
                        actorManager.delete(id);
                        break;
                    case DELETE_GENRE:
                        id = in.readInt();
                        genreManager.delete(id);
                        break;
                    case DELETE_USER:
                        id = in.readInt();
                        userManager.delete(id);

                        break;
                    case DELETE_USER_SUBSCRIPTION:
                        break;
                    case DELETE_USER_FILM:
                        break;
                    case DELETE_SUBSCRIPTION:
                        id = in.readInt();
                        subscriptionManager.delete(id);
                        break;
                    case GET_ALL_FILMS:
                        out.writeObject(filmManager.getAll());
                        out.flush();
                        break;
                    case GET_FILM:
                        id = (Integer) in.readObject();
                        out.writeObject(filmManager.getById(id));
                        out.flush();
                        break;
                    case GET_ACTOR:
                        id = (Integer) in.readObject();
                        out.writeObject(actorManager.getById(id));
                        out.flush();
                        break;
                    case GET_GENRE:
                        id = (Integer) in.readObject();
                        out.writeObject(genreManager.getById(id));
                        out.flush();
                        break;
                    case GET_ALL_SUBSCRIPTIONS:
                        out.writeObject(subscriptionManager.getAll());
                        out.flush();
                        break;
                    case GET_SUBSCRIPTION:
                        id = (Integer) in.readObject();
                        out.writeObject(subscriptionManager.getById(id));
                        out.flush();
                        break;
                    case GET_USER:
                        obj = in.readObject();
                        if (obj.getClass() == int.class) {
                            out.writeObject(userManager.getById((int) obj));
                        } else {
                            out.writeObject(userManager.getByName((String) obj));
                        }
                        out.flush();
                        break;
                    case ERROR:
                        break;
                    case CLOSE_CONNECTION:
                        break;
                }
                out.writeObject(FINISHED);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
