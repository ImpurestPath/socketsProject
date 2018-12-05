package ru.ifmo.db.domain;

import ru.ifmo.db.domain.dataAccessServices.Client;
import ru.ifmo.db.domain.guiServices.Server;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        new Server(client);
    }
}