package ru.ifmo.db.domain.guiServices.managers;


import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.guiServices.domainDTO.Genre;
import ru.ifmo.db.domain.mappers.TransformerToDTO;
import ru.ifmo.db.domain.mappers.TransformerToEntity;

public class GenreManager implements Manager<Genre> {
    private Client client;

    public GenreManager(Client client) {
        this.client = client;
    }

    @Override
    public Genre getById(int id) {
        return TransformerToEntity.toGenre(client.getGenre(id));
    }

    @Override
    public int add(Genre obj) {
        return client.addGenre(TransformerToDTO.toGenre(obj));
    }

    @Override
    public void update(Genre obj) {
        client.updateGenre(obj.getId(),TransformerToDTO.toGenre(obj));
    }

    @Override
    public void delete(int id) {
        client.deleteGenre(id);
    }
}
