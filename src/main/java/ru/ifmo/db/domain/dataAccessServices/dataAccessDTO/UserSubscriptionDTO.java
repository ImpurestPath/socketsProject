package ru.ifmo.db.domain.dataAccessServices.dataAccessDTO;

import java.util.Date;

public class UserSubscriptionDTO {
    private final int idSubscription;
    private final int idUser;
    private final Date start;
    private final Date finish;

    public UserSubscriptionDTO(int idSubscription, int idUser, Date start, Date finish) {
        this.idSubscription = idSubscription;
        this.idUser = idUser;
        this.start = start;
        this.finish = finish;
    }

    public int getIdSubscription() {
        return idSubscription;
    }

    public int getIdUser() {
        return idUser;
    }

    public Date getStart() {
        return start;
    }

    public Date getFinish() {
        return finish;
    }
}
