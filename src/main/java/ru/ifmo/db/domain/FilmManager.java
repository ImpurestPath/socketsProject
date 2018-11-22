package ru.ifmo.db.domain;

import ru.ifmo.db.domain.domainDTO.Film;

public class FilmManager {
    private final dataAccessClient dataAccessClient;
    FilmManager(dataAccessClient dataAccessClient){
        this.dataAccessClient = dataAccessClient;
    }
    public Film get(int id){
        dataAccessClient
    }

}
