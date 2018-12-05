package ru.ifmo.db.domain.guiServices.domainDTO;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;

import java.io.Serializable;
import java.util.List;

public class Subscription implements Serializable, hasId {
    private final int id;
    private final String name;
    private final List<Integer> films;
    private final List<SubscriptionCostDTO> costs;

    public Subscription(int id, String name, List<Integer> films, List<SubscriptionCostDTO> costs) {
        this.id = id;
        this.name = name;
        this.films = films;
        this.costs = costs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getFilms() {
        return films;
    }

    public List<SubscriptionCostDTO> getCosts() {
        return costs;
    }
}
