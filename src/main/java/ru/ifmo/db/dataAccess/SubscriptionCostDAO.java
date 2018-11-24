package ru.ifmo.db.dataAccess;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;

import java.util.List;

public interface SubscriptionCostDAO {
    List<SubscriptionCostDTO> getAll(int idSubscription);

    SubscriptionCostDTO getById(int id);

    int add(SubscriptionCostDTO subscriptionCostDTO);

    void update(int id, SubscriptionCostDTO subscriptionCostDTO);

    void delete(int id);
}
