package ru.ifmo.db.DTO;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private final int id;
    private final String userName;
    private final double balance;

    public UserDTO(int id, String userName, double balance) {
        this.id = id;
        this.userName = userName;
        this.balance = balance;
    }

    public UserDTO(String userName, double balance) {
        this.id = -1;
        this.userName = userName;
        this.balance = balance;
    }
}
