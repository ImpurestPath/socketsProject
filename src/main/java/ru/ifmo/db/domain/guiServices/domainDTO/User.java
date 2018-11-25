package ru.ifmo.db.domain.guiServices.domainDTO;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserPurchaseDTO;

import java.util.List;

public class User {
    private final int id;
    private final String username;
    private final double balance;
    private final List<UserPurchaseDTO> films;
    private final List<UserPurchaseDTO> subscriptions;

    public User(int id, String username, double balance, List<UserPurchaseDTO> films, List<UserPurchaseDTO> subscriptions) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.films = films;
        this.subscriptions = subscriptions;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public List<UserPurchaseDTO> getFilms() {
        return films;
    }

    public List<UserPurchaseDTO> getSubscriptions() {
        return subscriptions;
    }
}
