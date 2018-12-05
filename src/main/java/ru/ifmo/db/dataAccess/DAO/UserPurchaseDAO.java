package ru.ifmo.db.dataAccess.DAO;

import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserPurchaseDTO;

import java.util.List;

public interface UserPurchaseDAO extends getAllByIdDAO<UserPurchaseDTO> {
    List<UserPurchaseDTO> getById(int idTypeOfPurchase,int idUser);
    void add(int idCost, int idUser);
    void delete(UserPurchaseDTO dto);
}
