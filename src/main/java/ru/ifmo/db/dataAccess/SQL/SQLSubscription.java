package ru.ifmo.db.dataAccess.SQL;

import ru.ifmo.db.dataAccess.DTO.SubscriptionDTO;
import ru.ifmo.db.dataAccess.SubscriptionDAO;

import java.util.List;

public class SQLSubscription implements SubscriptionDAO {
    @Override
    public List<SubscriptionDTO> getAll() {
        return null;
    }

    @Override
    public SubscriptionDTO get(int id) {
        return null;
    }

    @Override
    public int add(SubscriptionDTO subscriptionDTO) {
        return 0;
    }

    @Override
    public void update(int id, SubscriptionDTO subscriptionDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
