package ru.ifmo.db.domain.dataAccessDTO;

import java.io.Serializable;

public class ActorDTO implements Serializable {
    private final int id;
    private final String name;

    public ActorDTO(int id, String name) {
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
