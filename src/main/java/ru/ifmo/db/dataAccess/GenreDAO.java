package ru.ifmo.db.dataAccess;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.GenreDTO;

import java.util.List;

public interface GenreDAO extends DAO<GenreDTO>, getAllByIdDAO<GenreDTO> {

}
