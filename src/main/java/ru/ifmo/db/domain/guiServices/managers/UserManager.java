package ru.ifmo.db.domain.guiServices.managers;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserDTO;
import ru.ifmo.db.domain.guiServices.domainDTO.User;
import ru.ifmo.db.domain.mappers.TransformerToDTO;
import ru.ifmo.db.domain.mappers.TransformerToEntity;

public class UserManager implements Manager<User> {
    private Client client;

    public UserManager(Client client) {
        this.client = client;
    }

    public User getByName(String username) {
        UserDTO userDTO = client.getUser(username);
        if (userDTO == null) return null;
        return TransformerToEntity.toUser(userDTO, client.getAllUserFilms(userDTO.getId()), client.getAllUserSubscriptions(userDTO.getId()));
    }

    @Override
    public User getById(int id) {
        //TODO
        return null;
    }

    @Override
    public int add(User obj) {
        return client.addUser(TransformerToDTO.toUser(obj));
    }

    @Override
    public void update(User obj) {
        client.updateUser(obj.getId(), TransformerToDTO.toUser(obj));
    }

    public void buyFilm(int idUser, int idFilmCost) {
        client.addUserFilm(idUser, idFilmCost);
    }

    public void buySubscription(int idUser, int idSubscriptionCost) {
        client.addUserSubscription(idUser, idSubscriptionCost);
    }

    @Override
    public void delete(int id) {
        client.deleteUser(id);
    }
}
