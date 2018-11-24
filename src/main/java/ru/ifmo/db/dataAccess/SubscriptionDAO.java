package ru.ifmo.db.dataAccess;



import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionDTO;

import java.util.List;

public interface SubscriptionDAO {
    List<SubscriptionDTO> getAll();

    SubscriptionDTO getById(int id);

    List<SubscriptionCostDTO> getAllCosts(int idSubscription);

    SubscriptionCostDTO getCost(int idCost);

    List<Integer> getFilms(int idSubscription);

    int add(SubscriptionDTO subscriptionDTO);

    void addFilm(int idSubscription, int idFilm);

    int addCost(SubscriptionCostDTO dto);

    void update(int id, SubscriptionDTO subscriptionDTO);

    void updateCost(int id, SubscriptionCostDTO dto);

    void delete(int id);

    void deleteFilm(int idSubscription, int idFilm);

    void deleteCost(int id);
}
