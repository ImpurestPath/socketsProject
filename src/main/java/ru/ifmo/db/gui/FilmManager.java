package ru.ifmo.db.gui;

import ru.ifmo.db.gui.mappers.TransformerToEntity;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmManager implements Manager<Film> {
    private Client client;
    private ActorManager actorManager;
    private GenreManager genreManager;


    public FilmManager(Client client, ActorManager actorManager, GenreManager genreManager) {
        this.client = client;
        this.actorManager = actorManager;
        this.genreManager = genreManager;
    }

    public List<Film> getAll() {
        List<ru.ifmo.db.domain.guiServices.domainDTO.Film> filmDTOs = client.getAll(ru.ifmo.db.domain.guiServices.domainDTO.Film.class);
        List<Film> films = new ArrayList<>();
        for (ru.ifmo.db.domain.guiServices.domainDTO.Film film : filmDTOs) {
            List<Actor> actorsGUI = new ArrayList<>();
            List<Genre> genreGUI = new ArrayList<>();
            for (int i : film.getActors()) actorsGUI.add(actorManager.getById(i));
            for (int i : film.getGenres()) genreGUI.add(genreManager.getById(i));
            films.add(TransformerToGUI.toFilm(client.get(film.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Film.class), actorsGUI, genreGUI));
        }
        return films;
    }

    @Override
    public Film getById(int id) {
        ru.ifmo.db.domain.guiServices.domainDTO.Film film = client.get(id, ru.ifmo.db.domain.guiServices.domainDTO.Film.class);
        List<Actor> actorsGUI = new ArrayList<>();
        List<Genre> genreGUI = new ArrayList<>();
        for (int i : film.getActors()) actorsGUI.add(actorManager.getById(i));
        for (int i : film.getGenres()) genreGUI.add(genreManager.getById(i));
        return TransformerToGUI.toFilm(client.get(film.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Film.class), actorsGUI, genreGUI);
    }

    @Override
    public int add(Film obj) {
        return client.add(TransformerToEntity.toFilm(obj));
    }

    @Override
    public void update(Film obj) {
        client.update(TransformerToEntity.toFilm(obj));
    }

    @Override
    public void delete(Film obj) {
        client.delete(TransformerToEntity.toFilm(obj));
    }
}
