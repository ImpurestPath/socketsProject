package ru.ifmo.db.domain;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmDTO;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;
import ru.ifmo.db.domain.mappers.TransformerToEntity;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        List<FilmDTO> films = client.getAllFilms();
        List<Film> domainFilms = new ArrayList<>();
        for (FilmDTO film :
                films) {
            domainFilms.add(TransformerToEntity.toFilm(
                    film,
                    client.getFilmActors(film.getId()),
                    client.getFilmGenres(film.getId()),
                    client.getFilmSubscriptions(film.getId()),
                    client.getAllFilmCosts(film.getId())));
        }
        System.out.println("Finished");
    }
}