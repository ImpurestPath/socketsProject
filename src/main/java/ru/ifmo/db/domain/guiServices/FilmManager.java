package ru.ifmo.db.domain.guiServices;

import ru.ifmo.db.domain.dataAccessServices.DataAccessClient;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;

public class FilmManager {
    private final DataAccessClient dataAccessClient;
    FilmManager(DataAccessClient dataAccessClient){
        this.dataAccessClient = dataAccessClient;
    }
    public Film get(int id){
        //DataAccessClient
        return null;
    }

}
