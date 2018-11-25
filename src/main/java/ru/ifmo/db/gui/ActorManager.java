package ru.ifmo.db.gui;

import ru.ifmo.db.gui.mappers.TransformerToEntity;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

import java.util.HashMap;
import java.util.Map;

public class ActorManager implements Manager<Actor> {
    private Client client;
    private Map<Integer, Actor> actors;

    public ActorManager(Client client) {
        this.client = client;
        this.actors = new HashMap<>();
    }

    @Override
    public Actor getById(int id) {
        if (!actors.containsKey(id))
            actors.put(id, TransformerToGUI.toActor(client.get(id, ru.ifmo.db.domain.guiServices.domainDTO.Actor.class)));
        return actors.get(id);
    }

    @Override
    public int add(Actor obj) {
        int id =  client.add(TransformerToEntity.toActor(obj));
        getById(id);
        return id;
    }


    @Override
    public void update(Actor obj) {
        client.update(TransformerToEntity.toActor(obj));
        actors.put(obj.getId(),TransformerToGUI.toActor(client.get(obj.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Actor.class)));
    }

    @Override
    public void delete(Actor obj) {
        client.delete(TransformerToEntity.toActor(obj));
        actors.remove(obj.getId());
    }
}
