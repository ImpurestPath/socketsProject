package ru.ifmo.db.dataAccess;

import ru.ifmo.db.dataAccess.DTO.SubscriptionCostDTO;
import ru.ifmo.db.dataAccess.DTO.SubscriptionDTO;

import java.util.List;

public interface SubscriptionDAO {
    List<SubscriptionDTO> getAll();
    SubscriptionDTO get(int id);
    int add(SubscriptionDTO subscriptionDTO);
    void addFilm(int idSubscription, int idFilm);
    int addCost(SubscriptionCostDTO dto);
    void update(int id, SubscriptionDTO subscriptionDTO);
    void updateCost(int id, SubscriptionCostDTO dto);
    void delete(int id);
    void deleteFilm(int idSubscription, int idFilm);
    void deleteCost(int id);
}
