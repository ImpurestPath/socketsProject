package ru.ifmo.db.gui;

import javafx.concurrent.Task;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserPurchaseDTO;
import ru.ifmo.db.gui.entity.Actor;
import ru.ifmo.db.gui.entity.Film;
import ru.ifmo.db.gui.entity.Genre;
import ru.ifmo.db.gui.entity.User;
import ru.ifmo.db.gui.mappers.TransformerToEntity;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

import java.util.*;

public class FilmRepository implements Manager<Film> {
    private Client client;
    private ActorManager actorManager;
    private GenreManager genreManager;
    private Map<Integer,Film> films = null;


    public FilmRepository(Client client, ActorManager actorManager, GenreManager genreManager) {
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
    public List<Film> getUserFilms(User user){
        List<Film> foundFilms = new ArrayList<>();
        Date now = new Date();
        for (UserPurchaseDTO userPurchaseDTO : user.getFilms()){
            Film film = films.get(userPurchaseDTO.getIdPurchase());
            if ( userPurchaseDTO.getFinish().after(now) && !foundFilms.contains(film))
                foundFilms.add(film);
        }
        return foundFilms;
    }
    public class Loader extends Task<Map<Integer,Film>>{
        @Override
        protected Map<Integer, Film> call() {
            List<ru.ifmo.db.domain.guiServices.domainDTO.Film> filmDTOs = client.getAll(ru.ifmo.db.domain.guiServices.domainDTO.Film.class);
            Map<Integer,Film> newFilms = new HashMap<>();
            for (ru.ifmo.db.domain.guiServices.domainDTO.Film film : filmDTOs) {
                List<Actor> actorsGUI = new ArrayList<>();
                List<Genre> genreGUI = new ArrayList<>();
                for (int i : film.getActors()) actorsGUI.add(actorManager.getById(i));
                for (int i : film.getGenres()) genreGUI.add(genreManager.getById(i));
                newFilms.put(film.getId(),TransformerToGUI.toFilm(client.get(film.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Film.class), actorsGUI, genreGUI));
            }
            films = newFilms;
            return newFilms;
        }
    }
    public void updateAll()  {
        /*List<ru.ifmo.db.domain.guiServices.domainDTO.Film> filmDTOs = client.getAll(ru.ifmo.db.domain.guiServices.domainDTO.Film.class);
        Map<Integer,Film> newFilms = new HashMap<>();
        for (ru.ifmo.db.domain.guiServices.domainDTO.Film film : filmDTOs) {
            List<Actor> actorsGUI = new ArrayList<>();
            List<Genre> genreGUI = new ArrayList<>();
            for (int i : film.getActors()) actorsGUI.add(actorManager.getById(i));
            for (int i : film.getGenres()) genreGUI.add(genreManager.getById(i));
            newFilms.put(film.getId(),TransformerToGUI.toFilm(client.get(film.getId(), ru.ifmo.db.domain.guiServices.domainDTO.Film.class), actorsGUI, genreGUI));
        }*/
        Thread t = new Thread(new Loader());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
