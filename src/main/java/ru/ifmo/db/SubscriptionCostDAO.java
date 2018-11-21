package ru.ifmo.db;

import ru.ifmo.db.DTO.SubscriptionCostDTO;

import java.util.List;

public interface SubscriptionCostDAO {
    List<SubscriptionCostDTO> getAll(int idFilm);
    SubscriptionCostDTO get(int id);
    int add(SubscriptionCostDTO subscriptionCostDTO);
    void update(int id, SubscriptionCostDTO subscriptionCostDTO);
    void delete(int id);
}
