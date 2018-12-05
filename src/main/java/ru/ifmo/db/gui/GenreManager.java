package ru.ifmo.db.gui;

import ru.ifmo.db.gui.entity.Genre;
import ru.ifmo.db.gui.mappers.TransformerToEntity;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

import java.util.HashMap;
import java.util.Map;

public class GenreManager implements Manager<Genre> {
    private Client client;
    private Map<Integer, Genre> genres;

    GenreManager(Client client) {
        this.client = client;
        this.genres = new HashMap<>();
    }

    @Override
    public Genre getById(int id) {
        if (!genres.containsKey(id))
            genres.put(id, TransformerToGUI.toGenre(client.get(id, ru.ifmo.db.domain.guiServices.domainDTO.Genre.class)));
        return genres.get(id);
    }

    @Override
    public int add(Genre obj) {
        int id =  client.add(TransformerToEntity.toGenre(obj));
        getById(id);
        return id;
    }

    @Override
    public void update(Genre obj) {
        client.update(TransformerToEntity.toGenre(obj));
        genres.put(obj.getId(),TransformerToGUI.toGenre(client.get(obj.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Genre.class)));
    }

    @Override
    public void delete(Genre obj) {
        client.delete(TransformerToEntity.toGenre(obj));
        genres.remove(obj.getId());
    }
}
