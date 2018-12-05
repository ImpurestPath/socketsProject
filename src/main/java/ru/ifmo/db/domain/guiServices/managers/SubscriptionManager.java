package ru.ifmo.db.domain.guiServices.managers;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionDTO;
import ru.ifmo.db.domain.guiServices.domainDTO.Subscription;
import ru.ifmo.db.domain.mappers.TransformerToDTO;
import ru.ifmo.db.domain.mappers.TransformerToEntity;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionManager implements Manager<Subscription> {
    private Client client;

    public SubscriptionManager(Client client) {
        this.client = client;
    }

    public List<Subscription> getAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        for (SubscriptionDTO subscriptionDTO : client.getAllSubscriptions()) {
            subscriptions.add(TransformerToEntity.toSubscription(subscriptionDTO, client.getSubscriptionFilms(subscriptionDTO.getId()), client.getAllSubscriptionCosts(subscriptionDTO.getId())));
        }
        return subscriptions;
    }

    @Override
    public Subscription getById(int id) {
        return TransformerToEntity.toSubscription(client.getSubscription(id), client.getSubscriptionFilms(id), client.getAllSubscriptionCosts(id));
    }

    @Override
    public int add(Subscription obj) {
        int id = client.addSubscription(TransformerToDTO.toSubscription(obj));
        if (id == -1) return -1;
        for (SubscriptionCostDTO cost : obj.getCosts()) client.addSubscriptionCost(cost);
        for (int film : obj.getFilms()) client.addSubscriptionFilm(id, film);
        return id;
    }

    @Override
    public void update(Subscription obj) {
        Subscription original = getById(obj.getId());
        client.updateSubscription(obj.getId(), TransformerToDTO.toSubscription(obj));
        for (int film : obj.getFilms())
            if (!original.getFilms().contains(film)) client.addSubscriptionFilm(obj.getId(), film);
        List<SubscriptionCostDTO> updated = new ArrayList<>();
        for (SubscriptionCostDTO cost : obj.getCosts())
            if (cost.getId() == -1) client.addSubscriptionCost(cost);
            else if (!original.getCosts().contains(cost)) {
                client.updateSubscriptionCost(cost.getId(), cost);
                updated.add(cost);
            }
        for (SubscriptionCostDTO cost : original.getCosts())
            if (!updated.contains(cost) && !obj.getCosts().contains(cost)) client.deleteSubscriptionCost(cost.getId());
    }

    @Override
    public void delete(int id) {
        client.deleteSubscription(id);
    }
}
