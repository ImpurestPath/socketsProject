package ru.ifmo.db.domain.guiServices;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmDTO;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;
import ru.ifmo.db.domain.mappers.TransformerToDTO;
import ru.ifmo.db.domain.mappers.TransformerToEntity;

import java.util.ArrayList;
import java.util.List;

public class FilmManager implements Manager<Film>{
    private final Client client;

    FilmManager(Client client) {
        this.client = client;
    }

    public Film getById(int id) {
        FilmDTO film = client.getFilm(id);
        if (film != null) {
            return TransformerToEntity.toFilm(
                    film,
                    client.getFilmActors(film.getId()),
                    client.getFilmGenres(film.getId()),
                    client.getFilmSubscriptions(film.getId()),
                    client.getAllFilmCosts(film.getId()));
        } else return null;
    }

    public List<Film> getAll() {
        List<Film> domainFilms = new ArrayList<>();
        for (FilmDTO film : client.getAllFilms()) {
            domainFilms.add(TransformerToEntity.toFilm(
                    film,
                    client.getFilmActors(film.getId()),
                    client.getFilmGenres(film.getId()),
                    client.getFilmSubscriptions(film.getId()),
                    client.getAllFilmCosts(film.getId())));
        }
        return domainFilms;
    }

    public int add(Film film) {
        int id = client.addFilm(TransformerToDTO.toFilm(film));
        if (id == -1) return -1;
        for (int genre : film.getGenres()) client.addFilmGenre(id, genre);
        for (int actor : film.getActors()) client.addFilmActor(id, actor);
        for (int subscription : film.getSubscriptions()) client.addFilmGenre(id, subscription);
        for (FilmCostDTO cost : film.getCosts()) client.addFilmCost(cost);
        return id;
    }

    public void update(Film film) {
        Film original = getById(film.getId());
        client.updateFilm(film.getId(), TransformerToDTO.toFilm(film));
        for (int genre : film.getGenres())
            if (!original.getGenres().contains(genre)) client.addFilmGenre(film.getId(), genre);
        for (int actor : film.getActors())
            if (!original.getActors().contains(actor)) client.addFilmActor(film.getId(), actor);
        for (int subscription : film.getSubscriptions())
            if (!original.getSubscriptions().contains(subscription))
                client.addFilmGenre(film.getId(), subscription);
        for (int genre : original.getGenres())
            if (!film.getGenres().contains(genre)) client.addFilmGenre(film.getId(), genre);
        for (int actor : original.getActors())
            if (!film.getActors().contains(actor)) client.addFilmActor(film.getId(), actor);
        for (int subscription : original.getSubscriptions())
            if (!film.getSubscriptions().contains(subscription))
                client.addFilmGenre(film.getId(), subscription);
        List<FilmCostDTO> updated = new ArrayList<>();
        for (FilmCostDTO cost : film.getCosts()) {
            if (cost.getId() == -1) client.addFilmCost(cost);
            else if (!original.getCosts().contains(cost)) {
                client.updateFilmCost(cost.getId(), cost);
                updated.add(cost);
            }
        }
        for (FilmCostDTO cost : original.getCosts()) {
            if (!updated.contains(cost) && !film.getCosts().contains(cost)) client.deleteFilmCost(cost.getId());
        }
    }
    public void delete(int id){
        client.deleteFilm(id);
    }
}


