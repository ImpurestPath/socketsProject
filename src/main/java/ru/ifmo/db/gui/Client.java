package ru.ifmo.db.gui;

import ru.ifmo.db.domain.guiServices.domainDTO.*;
import ru.ifmo.db.domain.guiServices.domainDTO.Actor;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;
import ru.ifmo.db.domain.guiServices.domainDTO.Genre;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import static ru.ifmo.db.domain.guiServices.Commands.*;

public class Client {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private static Client instanse = null;
    public static Client getInstance(){
        if (instanse == null ) return new Client();
        else return instanse;
    }

    private Client() {
        int serverPort = 3568; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public <T extends Serializable> int add(T t) {
        try {
            if (t.getClass() == Film.class) {
                out.writeObject(ADD_FILM);
            } else if (t.getClass() == Actor.class) {
                out.writeObject(ADD_ACTOR);
            } else if (t.getClass() == Genre.class) {
                out.writeObject(ADD_GENRE);
            } else if (t.getClass() == User.class) {
                out.writeObject(ADD_USER);
            } else if (t.getClass() == Subscription.class) {
                out.writeObject(ADD_SUBSCRIPTION);
            }
            else return -1;
            out.flush();
            out.writeObject(t);
            out.flush();
            int id = in.readInt();
            if (in.readObject() != FINISHED) throw new Exception();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public <T extends Serializable> void update(T t) {
        try {
            if (t.getClass() == Film.class) {
                out.writeObject(UPDATE_FILM);
            } else if (t.getClass() == Actor.class) {
                out.writeObject(UPDATE_ACTOR);
            } else if (t.getClass() == Genre.class) {
                out.writeObject(UPDATE_GENRE);
            } else if (t.getClass() == User.class) {
                out.writeObject(UPDATE_USER);
            } else if (t.getClass() == Subscription.class) {
                out.writeObject(UPDATE_SUBSCRIPTION);
            }
            else return;
            out.flush();
            out.writeObject(t);
            out.flush();
            if (in.readObject() != FINISHED) throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T extends hasId> void delete(T t) {
        try {
            if (t.getClass() == Film.class) {
                out.writeObject(DELETE_FILM);
            } else if (t.getClass() == Actor.class) {
                out.writeObject(DELETE_ACTOR);
            } else if (t.getClass() == Genre.class) {
                out.writeObject(DELETE_GENRE);
            } else if (t.getClass() == User.class) {
                out.writeObject(DELETE_USER);
            } else if (t.getClass() == Subscription.class) {
                out.writeObject(DELETE_SUBSCRIPTION);
            }
            else return;
            out.flush();
            out.writeObject(t.getId());
            out.flush();
            if (in.readObject() != FINISHED) throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public <C> C get(int id, Class<?> C){
        try {
            if (C == Film.class) {
                out.writeObject(GET_FILM);
            } else if (C == Actor.class) {
                out.writeObject(GET_ACTOR);
            } else if (C == Genre.class) {
                out.writeObject(GET_GENRE);
            } else if (C == User.class) {
                out.writeObject(GET_USER);
            } else if (C == Subscription.class) {
                out.writeObject(GET_SUBSCRIPTION);
            }
            else return null;
            out.flush();
            out.writeInt(id);
            out.flush();
            C object =  (C) in.readObject();
            if (in.readObject() != FINISHED) throw new Exception();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public <C> List<C> getAll(Class<?> C){
        try {
            if (C == Film.class) {
                out.writeObject(GET_ALL_FILMS);
            } else if (C == Subscription.class) {
                out.writeObject(GET_ALL_SUBSCRIPTIONS);
            }
            else return null;
            out.flush();
            List<C> object =  (List<C>) in.readObject();
            out.flush();
            if (in.readObject() != FINISHED) throw new Exception();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
