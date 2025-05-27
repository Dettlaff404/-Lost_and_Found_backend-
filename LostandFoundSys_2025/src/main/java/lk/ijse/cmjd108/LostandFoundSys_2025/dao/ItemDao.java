package lk.ijse.cmjd108.LostandFoundSys_2025.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lk.ijse.cmjd108.LostandFoundSys_2025.entities.ItemEntity;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, String> {

    //methord to get a ItemEntity by requestId on the requestEntity field
    @Query("SELECT i FROM ItemEntity i WHERE i.requestEntity.requestId = :requestId")
    public ItemEntity findByRequestId(String requestId);

}
