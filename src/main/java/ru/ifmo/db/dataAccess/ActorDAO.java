package ru.ifmo.db.dataAccess;


import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.ActorDTO;

import java.util.List;

public interface ActorDAO extends DAO<ActorDTO>, getAllByIdDAO<ActorDTO> {

}
