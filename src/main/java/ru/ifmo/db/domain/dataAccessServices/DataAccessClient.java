package ru.ifmo.db.domain.dataAccessServices;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import static ru.ifmo.db.domain.dataAccessServices.Commands.*;

public class DataAccessClient {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    DataAccessClient() {
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

    <T extends Serializable> int addObject(T object) {
        if (object.getClass() == ActorDTO.class) {
            return sendObject(ADD_ACTOR, object);
        } else if (object.getClass() == FilmCostDTO.class) {
            return sendObject(ADD_FILM_COST, object);
        } else if (object.getClass() == FilmDTO.class) {
            return sendObject(ADD_FILM, object);
        } else if (object.getClass() == GenreDTO.class) {
            return sendObject(ADD_GENRE, object);
        } else if (object.getClass() == SubscriptionCostDTO.class) {
            return sendObject(ADD_SUBSCRIPTION_COST, object);
        } else if (object.getClass() == SubscriptionDTO.class) {
            return sendObject(ADD_SUBSCRIPTION, object);
        } else if (object.getClass() == UserDTO.class) {
            return sendObject(ADD_USER, object);
        } else return -1;
    }

    private void sendTwoInt(Commands command, int i1, int i2) {
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

    <T extends Serializable> void sendIntAndObject(Commands command, int id, T object) {
        try {
            out.writeObject(command);
            out.flush();
            out.writeInt(id);
            out.flush();
            out.writeObject(object);
            out.flush();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T extends Serializable> int sendObject(Commands command, T object) {
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

    private void sendInt(Commands command, int i) {
        try {
            out.writeObject(command);
            out.flush();
            out.writeInt(i);
            out.flush();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private List<Integer> readIntegers(Commands getSubscriptionFilms, int id) {
        try {
            out.writeObject(getSubscriptionFilms);
            out.flush();
            out.writeInt(id);
            out.flush();
            List<Integer> dto = (List<Integer>) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    int addFilm(FilmDTO dto) {
        return addObject(dto);
    }

    void addFilmGenre(int idFilm, int idGenre) {
        sendTwoInt(ADD_FILM_GENRE, idFilm, idGenre);
    }

    int addFilmCost(FilmCostDTO dto) {
        return addObject(dto);
    }

    void addFilmActor(int idFilm, int idActor) {
        sendTwoInt(ADD_FILM_ACTOR, idFilm, idActor);
    }

    int addActor(ActorDTO dto) {
        return addObject(dto);
    }

    int addGenre(GenreDTO dto) {
        return addObject(dto);
    }

    int addUser(UserDTO dto) {
        return addObject(dto);
    }

    void addUserSubscription(int idUser, int idSubscriptionCost) {
        sendTwoInt(ADD_USER_SUBSCRIPTION, idUser, idSubscriptionCost);
    }

    void addUserFilm(int idUser, int idFilmCost) {
        sendTwoInt(ADD_USER_FILM, idUser, idFilmCost);
    }

    int addSubscription(SubscriptionDTO dto) {
        return addObject(dto);
    }

    int addSubscriptionCost(SubscriptionCostDTO dto) {
        return addObject(dto);
    }

    void addSubscriptionFilm(int idSubscription, int idFilm) {
        sendTwoInt(ADD_SUBSCRIPTION_FILM, idSubscription, idFilm);
    }

    void updateFilm(int id, FilmDTO filmDTO) {
        sendIntAndObject(UPDATE_FILM, id, filmDTO);
    }

    void updateFilmCost(int id, FilmCostDTO dto) {
        sendIntAndObject(UPDATE_FILM_COST, id, dto);
    }

    void updateActor(int id, ActorDTO dto) {
        sendIntAndObject(UPDATE_ACTOR, id, dto);
    }

    void updateGenre(int id, GenreDTO dto) {
        sendIntAndObject(UPDATE_GENRE, id, dto);
    }

    void updateUser(int id, UserDTO dto) {
        sendIntAndObject(UPDATE_USER, id, dto);
    }

    void updateSubscription(int id, SubscriptionDTO dto) {
        sendIntAndObject(UPDATE_SUBSCRIPTION, id, dto);
    }

    void updateSubscriptionCost(int id, SubscriptionCostDTO dto) {
        sendIntAndObject(UPDATE_SUBSCRIPTION_COST, id, dto);
    }

    void deleteFilm(int id) {
        sendInt(DELETE_FILM, id);
    }

    void deleteFilmGenre(int idFilm, int idGenre) {
        sendTwoInt(DELETE_FILM_GENRE, idFilm, idGenre);
    }

    void deleteFilmCost(int id) {
        sendInt(DELETE_FILM_COST, id);
    }

    void deleteFilmActor(int idFilm, int idActor) {
        sendTwoInt(DELETE_FILM_ACTOR, idFilm, idActor);
    }

    void deleteActor(int id) {
        sendInt(DELETE_ACTOR, id);
    }

    void deleteGenre(int id) {
        sendInt(DELETE_GENRE, id);
    }

    void deleteUser(int id) {
        sendInt(DELETE_USER, id);
    }

    void deleteUserSubscription(int idUser, int idSubscription) {
        //TODO
        sendTwoInt(DELETE_USER_SUBSCRIPTION, idUser, idSubscription);
    }

    void deleteUserFilm(int idUser, int idFilm) {
        sendTwoInt(DELETE_USER_FILM, idUser, idFilm);
    }

    void deleteSubscription(int id) {
        sendInt(DELETE_SUBSCRIPTION, id);
    }

    void deleteSubscriptionCost(int id) {
        sendInt(DELETE_SUBSCRIPTION_COST, id);
    }

    void deleteSubscriptionFilm(int idSubscription, int idFilm) {
        sendTwoInt(DELETE_SUBSCRIPTION_FILM, idSubscription, idFilm);
    }

    List<FilmDTO> getAllFilms() {
        try {
            out.writeObject(GET_ALL_FILMS);
            out.flush();
            List<FilmDTO> dto = (List<FilmDTO>) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    FilmDTO getFilm(int id) {
        try {
            out.writeObject(GET_FILM);
            out.flush();
            out.writeInt(id);
            out.flush();
            FilmDTO dto = (FilmDTO) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    List<FilmCostDTO> getAllFilmCosts(int idFilm) {
        try {
            out.writeObject(GET_ALL_FILM_COSTS);
            out.flush();
            out.writeInt(idFilm);
            out.flush();
            List<FilmCostDTO> dto = (List<FilmCostDTO>) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    FilmCostDTO getFilmCost(int idFilmCost) {
        try {
            out.writeObject(GET_FILM_COST);
            out.flush();
            out.writeInt(idFilmCost);
            out.flush();
            FilmCostDTO dto = (FilmCostDTO) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    List<Integer> getFilmSubscriptions(int idFilm) {
        return readIntegers(GET_FILM_SUBSCRIPTIONS, idFilm);
    }

    List<Integer> getFilmActors(int idFilm) {
        return readIntegers(GET_FILM_ACTORS, idFilm);
    }

    List<Integer> getFilmGenres(int idFilm) {
        return readIntegers(GET_FILM_GENRES, idFilm);
    }

    ActorDTO getActor(int id) {
        try {
            out.writeObject(GET_ACTOR);
            out.flush();
            out.writeInt(id);
            out.flush();
            ActorDTO dto = (ActorDTO) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    GenreDTO getGenre(int id) {
        try {
            out.writeObject(GET_GENRE);
            out.flush();
            out.writeInt(id);
            out.flush();
            GenreDTO dto = (GenreDTO) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    List<SubscriptionDTO> getAllSubscriptions() {
        try {
            out.writeObject(GET_ALL_SUBSCRIPTIONS);
            out.flush();
            List<SubscriptionDTO> dto = (List<SubscriptionDTO>) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    SubscriptionDTO getSubscription(int id) {
        try {
            out.writeObject(GET_SUBSCRIPTION);
            out.flush();
            out.writeInt(id);
            out.flush();
            SubscriptionDTO dto = (SubscriptionDTO) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    List<SubscriptionCostDTO> getAllSubscriptionCosts(int idSubscription) {
        try {
            out.writeObject(GET_ALL_SUBSCRIPTION_COSTS);
            out.flush();
            out.writeInt(idSubscription);
            out.flush();
            List<SubscriptionCostDTO> dto = (List<SubscriptionCostDTO>) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    SubscriptionCostDTO getSubscriptionCost(int idSubscriptionCost) {
        try {
            out.writeObject(GET_SUBSCRIPTION_COST);
            out.flush();
            out.writeInt(idSubscriptionCost);
            out.flush();
            SubscriptionCostDTO dto = (SubscriptionCostDTO) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    List<Integer> getSubscriptionFilms(int idSubscription) {
        return readIntegers(GET_SUBSCRIPTION_FILMS, idSubscription);
    }

    UserDTO getUser(String userName) {
        try {
            out.writeObject(GET_SUBSCRIPTION_COST);
            out.flush();
            out.writeUTF(userName);
            out.flush();
            UserDTO dto = (UserDTO) in.readObject();
            if (in.readObject() != FINISHED) {
                throw new Exception();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
