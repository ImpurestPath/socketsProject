package ru.ifmo.db;

import ru.ifmo.db.DTO.SubscriptionDTO;

import java.util.List;

public interface SubscriptionDAO {
    List<SubscriptionDTO> getAll();
    SubscriptionDTO get(int id);
    int add(SubscriptionDTO subscriptionDTO);
    void update(int id, SubscriptionDTO subscriptionDTO);
    void delete(int id);
}
