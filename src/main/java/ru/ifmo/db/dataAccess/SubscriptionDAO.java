package ru.ifmo.db.dataAccess;

import ru.ifmo.db.dataAccess.DTO.SubscriptionDTO;

import java.util.List;

public interface SubscriptionDAO {
    List<SubscriptionDTO> getAll();
    SubscriptionDTO get(int id);
    int add(SubscriptionDTO subscriptionDTO);
    void update(int id, SubscriptionDTO subscriptionDTO);
    void delete(int id);
}
