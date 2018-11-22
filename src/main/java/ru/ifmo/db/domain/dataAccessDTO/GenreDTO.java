package ru.ifmo.db.domain.dataAccessDTO;

import java.io.Serializable;

public class GenreDTO implements Serializable {
    private final int id;
    private final String name;

    public GenreDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreDTO(String name) {
        this.id = -1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
