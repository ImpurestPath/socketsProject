package ru.ifmo.db.dataAccess.DAO;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionCostDTO;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.SubscriptionDTO;

import java.util.List;

public interface SubscriptionDAO extends DAO<SubscriptionDTO>, getAllDAO<SubscriptionDTO> {

    List<SubscriptionCostDTO> getAllCosts(int idSubscription);

    SubscriptionCostDTO getCost(int idCost);

    List<Integer> getFilms(int idSubscription);

    void addFilm(int idSubscription, int idFilm);

    int addCost(SubscriptionCostDTO dto);

    void updateCost(int id, SubscriptionCostDTO dto);

    void deleteFilm(int idSubscription, int idFilm);

    void deleteCost(int id);
}
