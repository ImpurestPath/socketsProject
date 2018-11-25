package ru.ifmo.db.domain;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmDTO;
import ru.ifmo.db.domain.guiServices.Server;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;
import ru.ifmo.db.domain.mappers.TransformerToEntity;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        new Server(client);
    }
}