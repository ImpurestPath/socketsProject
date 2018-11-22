package ru.ifmo.db.dataAccess;

import ru.ifmo.db.dataAccess.DTO.SubscriptionCostDTO;

import java.util.List;

public interface SubscriptionCostDAO {
    List<SubscriptionCostDTO> getAll(int idSubscription);

    SubscriptionCostDTO get(int id);

    int add(SubscriptionCostDTO subscriptionCostDTO);

    void update(int id, SubscriptionCostDTO subscriptionCostDTO);

    void delete(int id);
}
