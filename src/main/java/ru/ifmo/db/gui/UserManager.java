package ru.ifmo.db.gui;


import ru.ifmo.db.gui.mappers.TransformerToEntity;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

public class UserManager implements Manager<User> {
    private Client client;

    public UserManager(Client client) {
        this.client = client;
    }

    public User getByName(String username) {
        return TransformerToGUI.toUser(client.get(username, ru.ifmo.db.domain.guiServices.domainDTO.User.class));
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public int add(User obj) {
        return client.add(TransformerToEntity.toUser(obj));
    }

    @Override
    public void update(User obj) {
        client.update(TransformerToEntity.toUser(obj));
    }

    @Override
    public void delete(User obj) {
        client.delete(TransformerToEntity.toUser(obj));
    }
}
