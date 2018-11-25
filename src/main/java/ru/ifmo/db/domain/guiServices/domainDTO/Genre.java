package ru.ifmo.db.domain.guiServices.domainDTO;

import java.io.Serializable;

public class Genre implements Serializable,hasId {
    private final int id;
    private final String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
