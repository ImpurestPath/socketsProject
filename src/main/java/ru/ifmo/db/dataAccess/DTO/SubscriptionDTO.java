package ru.ifmo.db.dataAccess.DTO;

import java.io.Serializable;

public class SubscriptionDTO implements Serializable {
    private final int id;
    private final String name;
    public SubscriptionDTO(int id, String name){
        this.id = id;
        this.name = name;
    }
    SubscriptionDTO(String name){
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