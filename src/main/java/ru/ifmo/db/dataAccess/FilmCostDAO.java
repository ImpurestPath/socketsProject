package ru.ifmo.db.dataAccess;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;

import java.util.List;

public interface FilmCostDAO extends DAO<FilmCostDTO>, getAllByIdDAO<FilmCostDTO> {

}
