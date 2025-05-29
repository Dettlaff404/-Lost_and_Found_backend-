package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.ItemDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.RequestDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.ItemEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.RequestEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.ItemNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.RequestNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.ItemService;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.EntityDTO_Convertor;
// import lk.ijse.cmjd108.LostandFoundSys_2025.util.UtilData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService_IMPL implements ItemService {

    private final ItemDao itemDao;
    private final RequestDao requestDao;
    private final EntityDTO_Convertor entityDTOConvertor;

    // @Override
    // public void addItem(ItemDTO itemDTO) {

    //     itemDTO.setItemId(UtilData.generateItemId());
    //     itemDTO.setClaimedUserId(null);

    //     itemDao.save(entityDTOConvertor.itemDTOToItemEntity(itemDTO));

    //     System.out.println("Item added Successfully");
    // }

    @Override
    public void deleteItem(String itemId) {

        Optional<ItemEntity> foundItem = itemDao.findById(itemId);
        if (foundItem.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }
        
        itemDao.deleteById(itemId);
        System.out.println("Item " + itemId + " deleted Successfully");
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {

        Optional<ItemEntity> foundItem = itemDao.findById(itemId);
        if (foundItem.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }

        Optional<RequestEntity> requestEntity = requestDao.findById(itemDTO.getRequestId());  

        if (requestEntity.isEmpty()) {
            throw new RequestNotFoundException("Request not found");
        } 
        
        requestEntity.get().setItemName(itemDTO.getItemName());
        requestEntity.get().setDescription(itemDTO.getDescription());
        requestEntity.get().setLocation(itemDTO.getLocation());
        requestEntity.get().setDate(itemDTO.getDate());
        requestEntity.get().setItemStatus(itemDTO.getStatus());

        itemDao.save(entityDTOConvertor.itemDTOToItemEntity(itemDTO));
        System.out.println("Item " + itemId + " updated Successfully");
    }

    @Override
    public ItemDTO getSelectedItem(String itemId) {
        
        Optional<ItemEntity> foundItem = itemDao.findById(itemId);
        if (foundItem.isEmpty()) {
            throw new ItemNotFoundException("Selected Item not found");
        }
        
        return entityDTOConvertor.itemEntityToItemDTO(foundItem.get());
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return entityDTOConvertor.toItemDTOsList(itemDao.findAll());
    }
}
