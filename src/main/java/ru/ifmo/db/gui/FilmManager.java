package ru.ifmo.db.gui;

import javafx.collections.FXCollections;
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
    private Map<Integer,Film> films = null;


    public FilmManager(Client client, ActorManager actorManager, GenreManager genreManager) {
        this.client = client;
        this.actorManager = actorManager;
        this.genreManager = genreManager;

    }

    public List<Film> getAll() {
        if (films == null) updateAll();
        return new ArrayList<>(films.values());
    }
    public List<Film> getAllByPartOfName(String name){
        List<Film> foundFilms = new ArrayList<>();
        for (Film film: films.values()){
            if (film.getName().toLowerCase().contains(name.toLowerCase())) foundFilms.add(film);
        }
        return foundFilms;
    }
    public void updateAll(){
        List<ru.ifmo.db.domain.guiServices.domainDTO.Film> filmDTOs = client.getAll(ru.ifmo.db.domain.guiServices.domainDTO.Film.class);
        Map<Integer,Film> newFilms = new HashMap<>();
        for (ru.ifmo.db.domain.guiServices.domainDTO.Film film : filmDTOs) {
            List<Actor> actorsGUI = new ArrayList<>();
            List<Genre> genreGUI = new ArrayList<>();
            for (int i : film.getActors()) actorsGUI.add(actorManager.getById(i));
            for (int i : film.getGenres()) genreGUI.add(genreManager.getById(i));
            newFilms.put(film.getId(),TransformerToGUI.toFilm(client.get(film.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Film.class), actorsGUI, genreGUI));
        }
        this.films = newFilms;
    }
    @Override
    public Film getById(int id) {
        if (films.containsKey(id)) return films.get(id);
        else {
            updateById(id);
            return films.get(id);
        }
    }
    public void updateById(int id){
        ru.ifmo.db.domain.guiServices.domainDTO.Film film = client.get(id, ru.ifmo.db.domain.guiServices.domainDTO.Film.class);
        List<Actor> actorsGUI = new ArrayList<>();
        List<Genre> genreGUI = new ArrayList<>();
        for (int i : film.getActors()) actorsGUI.add(actorManager.getById(i));
        for (int i : film.getGenres()) genreGUI.add(genreManager.getById(i));
        films.replace(id,TransformerToGUI.toFilm(client.get(film.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Film.class), actorsGUI, genreGUI));
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
