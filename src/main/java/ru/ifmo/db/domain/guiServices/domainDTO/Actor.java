package ru.ifmo.db.domain.guiServices.domainDTO;

import java.io.Serializable;

public class Actor implements Serializable,hasId {
    private final int id;
    private final String name;

    public Actor(int id, String name)  {
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
