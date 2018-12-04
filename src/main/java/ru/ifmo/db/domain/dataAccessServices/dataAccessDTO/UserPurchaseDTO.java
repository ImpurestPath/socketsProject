package ru.ifmo.db.domain.dataAccessServices.dataAccessDTO;

import java.io.Serializable;
import java.util.Date;

public class UserPurchaseDTO implements Serializable {
    private final int idPurchase;
    private final int idUser;
    private final Date start;
    private final Date finish;


    public UserPurchaseDTO(int idPurchase, int idUser, Date start, Date finish) {
        this.idPurchase = idPurchase;
        this.idUser = idUser;
        this.start = start;
        this.finish = finish;
    }

    public int getIdPurchase() {
        return idPurchase;
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
