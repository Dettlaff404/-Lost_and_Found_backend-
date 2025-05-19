package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.ItemService;

@Service
public class ItemService_IMPL implements ItemService {

    @Override
    public void addItem(ItemDTO itemDTO) {
       System.out.println("Item added Successfully");
       System.out.println(itemDTO);
    }

    @Override
    public void deleteItem(String itemId) {
        
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        
    }

    @Override
    public ItemDTO getSelectedItem(String itemId) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return List.of();
    }

    

}
