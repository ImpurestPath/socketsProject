package ru.ifmo.db.gui;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.hasCost;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;
import ru.ifmo.db.gui.entity.User;
import ru.ifmo.db.gui.mappers.TransformerToEntity;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

public class UserManager implements Manager<User> {
    private Client client;
    private User current;

    UserManager(Client client) {
        this.client = client;
    }

    public User getByName(String username) {
        return TransformerToGUI.toUser(client.get(username, ru.ifmo.db.domain.guiServices.domainDTO.User.class));
    }
    public<T extends hasCost> void buy(User user, T cost) throws Exception{
        if (cost.getClass() != FilmCostDTO.class && cost.getClass() != SubscriptionCostDTO.class) throw new Exception();
        if (user.getBalance() < cost.getCost()) throw new Exception();
        client.buy(user.getId(),cost);
        current = getByName(user.getUsername());
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public User getCurrent() {
        return current;
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
