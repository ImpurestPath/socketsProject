package ru.ifmo.db.gui;

import ru.ifmo.db.gui.entity.Subscription;
import ru.ifmo.db.gui.mappers.TransformerToEntity;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionManager implements Manager<Subscription> {
    private Client client;
    private Map<Integer, Subscription> subscriptions;

    public SubscriptionManager(Client client) {
        this.client = client;
        this.subscriptions = new HashMap<>();
    }
    public List<Subscription> getAll(){
        List<ru.ifmo.db.domain.guiServices.domainDTO.Subscription> subscriptionsDTO = client.getAll(ru.ifmo.db.domain.guiServices.domainDTO.Subscription.class);
        List<Subscription> subscriptions = new ArrayList<>();
        for (ru.ifmo.db.domain.guiServices.domainDTO.Subscription subscription : subscriptionsDTO){
            subscriptions.add(TransformerToGUI.toSubscription(subscription));
        }
        return subscriptions;
    }
    @Override
    public Subscription getById(int id) {
        return TransformerToGUI.toSubscription(client.get(id, ru.ifmo.db.domain.guiServices.domainDTO.Subscription.class));
    }

    @Override
    public int add(Subscription obj) {
        return client.add(TransformerToEntity.toSubscription(obj));
    }

    @Override
    public void update(Subscription obj) {
        client.update(TransformerToEntity.toSubscription(obj));
    }

    @Override
    public void delete(Subscription obj) {
        client.delete(TransformerToEntity.toSubscription(obj));
    }
}
