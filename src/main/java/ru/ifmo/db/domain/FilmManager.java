package ru.ifmo.db.domain;

import ru.ifmo.db.domain.dataAccessServices.dataAccessClient;
import ru.ifmo.db.domain.domainDTO.Film;

public class FilmManager {
    private final ru.ifmo.db.domain.dataAccessServices.dataAccessClient dataAccessClient;
    FilmManager(dataAccessClient dataAccessClient){
        this.dataAccessClient = dataAccessClient;
    }
    public Film get(int id){
        //dataAccessClient
        return null;
    }

}
