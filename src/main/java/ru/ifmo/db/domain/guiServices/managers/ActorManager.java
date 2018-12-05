package ru.ifmo.db.domain.guiServices.managers;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.guiServices.domainDTO.Actor;
import ru.ifmo.db.domain.mappers.TransformerToDTO;
import ru.ifmo.db.domain.mappers.TransformerToEntity;

public class ActorManager implements Manager<Actor> {
    private Client client;

    public ActorManager(Client client) {
        this.client = client;
    }

    @Override
    public Actor getById(int id) {
        return TransformerToEntity.toActor(client.getActor(id));
    }

    @Override
    public int add(Actor obj) {
        return client.addActor(TransformerToDTO.toActor(obj));
    }

    @Override
    public void update(Actor obj) {
        client.updateActor(obj.getId(), TransformerToDTO.toActor(obj));
    }

    @Override
    public void delete(int id) {
        client.deleteActor(id);
    }
}
