package ru.ifmo.db.domain.dataAccessServices.dataAccessDTO;

import java.io.Serializable;

public class SubscriptionCostDTO implements Serializable {
    private final int id;
    private final int duration;
    private final double cost;
    private final int idSubscription;

    public SubscriptionCostDTO( int id,int idSubscription, int duration, double cost) {
        this.id = id;
        this.duration = duration;
        this.cost = cost;
        this.idSubscription = idSubscription;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    public int getIdSubscription() {
        return idSubscription;
    }
}
