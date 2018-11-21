package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DTO.SubscriptionCostDTO;
import ru.ifmo.db.dataAccess.SubscriptionCostDAO;

import java.util.List;

public class SQLSubscriptionCost implements SubscriptionCostDAO {
    @Override
    public List<SubscriptionCostDTO> getAll(int idFilm) {
        return null;
    }

    @Override
    public SubscriptionCostDTO get(int id) {
        return null;
    }

    @Override
    public int add(SubscriptionCostDTO subscriptionCostDTO) {
        return 0;
    }

    @Override
    public void update(int id, SubscriptionCostDTO subscriptionCostDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
