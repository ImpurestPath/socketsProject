package ru.ifmo.db.domain.dataAccessServices.dataAccessDTO;

import java.util.Date;

public class UserFilmDTO {
    private final int idFilm;
    private final int idUser;
    private final Date start;
    private final Date finish;


    public UserFilmDTO(int idFilm, int idUser, Date start, Date finish) {
        this.idFilm = idFilm;
        this.idUser = idUser;
        this.start = start;
        this.finish = finish;
    }

    public int getIdFilm() {
        return idFilm;
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
