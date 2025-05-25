package lk.ijse.cmjd108.LostandFoundSys_2025.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ItemStatus;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.ItemEntity;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, String> {

    //methord to get list of claimed items
    public List<ItemEntity> findByStatus(ItemStatus status);

    //methord to get a ItemEntity by requestId
    public ItemEntity findByRequestId(String requestId);

}
