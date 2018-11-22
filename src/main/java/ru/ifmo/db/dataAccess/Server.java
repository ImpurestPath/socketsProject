package ru.ifmo.db.dataAccess;


import ru.ifmo.db.dataAccess.DTO.*;
import ru.ifmo.db.dataAccess.SQL.SQLConnection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Server() {
        ConnectionDAO connection = new SQLConnection();
        int port = 3567; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            final ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            final ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


            Command command;
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

            while (true) {
                command =(Command)in.readObject(); // ожидаем пока клиент пришлет строку текста.
                out.writeObject(Command.FINISHED);
                switch (command){
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
                        connection.updateFilm(id,filmDTO);
                        break;
                    case UPDATE_FILM_COST:
                        id = in.readInt();
                        filmCostDTO = (FilmCostDTO) in.readObject();
                        connection.updateFilmCost(id,filmCostDTO);
                        break;
                    case UPDATE_ACTOR:
                        id = in.readInt();
                        actorDTO = (ActorDTO) in.readObject();
                        connection.updateActor(id,actorDTO);
                        break;
                    case UPDATE_GENRE:
                        id = in.readInt();
                        genreDTO = (GenreDTO) in.readObject();
                        connection.updateGenre(id,genreDTO);
                        break;
                    case UPDATE_USER:
                        id = in.readInt();
                        userDTO = (UserDTO) in.readObject();
                        connection.updateUser(id,userDTO);
                        break;
                    case UPDATE_SUBSCRIPTION:
                        id = in.readInt();
                        subscriptionDTO = (SubscriptionDTO) in.readObject();
                        connection.updateSubscription(id,subscriptionDTO);
                        break;
                    case UPDATE_SUBSCRIPTION_COST:
                        id = in.readInt();
                        subscriptionCostDTO = (SubscriptionCostDTO) in.readObject();
                        connection.updateSubscriptionCost(id,subscriptionCostDTO);
                        break;
                    case DELETE_FILM:
                        id = in.readInt();
                        connection.deleteFilm(id);
                        break;
                    case DELETE_FILM_GENRE:
                        idFilm = in.readInt();
                        idGenre = in.readInt();
                        connection.deleteFilmGenre(idFilm,idGenre);
                        break;
                    case DELETE_FILM_COST:
                        id = in.readInt();
                        connection.deleteFilmCost(id);
                        break;
                    case DELETE_FILM_ACTOR:
                        idFilm = in.readInt();
                        idActor = in.readInt();
                        connection.deleteFilmActor(idFilm,idActor);
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
                        connection.deleteUserSubscription(idUser,idSubscription);
                        break;
                    case DELETE_USER_FILM:
                        idUser = in.readInt();
                        idFilm = in.readInt();
                        connection.deleteUserFilm(idUser,idFilm);
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
                        connection.deleteSubscriptionFilm(idSubscription,idFilm);
                        break;
                    case FINISHED:
                        break;
                }
                out.writeObject(Command.FINISHED);
                //out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("Waiting for the next line...");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
