package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.ItemService;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.UtilData;

@Service
public class ItemService_IMPL implements ItemService {

    @Override
    public void addItem(ItemDTO itemDTO) {
        itemDTO.setItemId(UtilData.generateItemId());
        System.out.println(itemDTO);
        System.out.println("Item added Successfully");
    }

    @Override
    public void deleteItem(String itemId) {
        System.out.println("Item deleted Successfully");
        System.out.println(itemId);
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        System.out.println("Item updated Successfully");
        System.out.println(itemId);
        System.out.println(itemDTO);
    }

    @Override
    public ItemDTO getSelectedItem(String itemId) {
        return new ItemDTO();
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return new ArrayList<ItemDTO>();
    }

}
