package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.ItemDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.RequestDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ReqStatus;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.ItemEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.RequestEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.ItemNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.ItemService;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.EntityDTO_Convertor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService_IMPL implements ItemService {

    private final ItemDao itemDao;
    private final RequestDao requestDao;
    private final EntityDTO_Convertor entityDTOConvertor;

    @Override
    public void deleteItem(String itemId) {

        Optional<ItemEntity> foundItem = itemDao.findById(itemId);
        if (foundItem.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }

        ItemEntity item = foundItem.get();
        RequestEntity associatedRequest = item.getRequestEntity();
        
        // Set the request status back to REJECTED since the item is being deleted
        if (associatedRequest != null) {
            associatedRequest.setStatus(ReqStatus.REJECTED);
            associatedRequest.setItem(null);
            requestDao.save(associatedRequest);
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

        ItemEntity existingItem = foundItem.get();
        RequestEntity associatedRequest = existingItem.getRequestEntity();

        // Update the associated request if it exists
        if (associatedRequest != null) {
            associatedRequest.setItemName(itemDTO.getItemName());
            associatedRequest.setDescription(itemDTO.getDescription());
            associatedRequest.setLocation(itemDTO.getLocation());
            associatedRequest.setDate(itemDTO.getDate());
            associatedRequest.setItemStatus(itemDTO.getStatus());
            requestDao.save(associatedRequest);
        }

        // Update the item
        existingItem.setItemName(itemDTO.getItemName());
        existingItem.setDescription(itemDTO.getDescription());
        existingItem.setLocation(itemDTO.getLocation());
        existingItem.setDate(itemDTO.getDate());
        existingItem.setStatus(itemDTO.getStatus());
        
        // Handle claimed user update
        if (itemDTO.getClaimedUserId() != null && !itemDTO.getClaimedUserId().isEmpty()) {
            try {
                existingItem.setClaimedUser(entityDTOConvertor.getUserById(itemDTO.getClaimedUserId()));
            } catch (Exception e) {
                System.err.println("Warning: Could not find user with ID: " + itemDTO.getClaimedUserId());
                existingItem.setClaimedUser(null);
            }
        } else {
            existingItem.setClaimedUser(null);
        }

        itemDao.save(existingItem);
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