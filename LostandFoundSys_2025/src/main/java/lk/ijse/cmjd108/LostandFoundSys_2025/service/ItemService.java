package lk.ijse.cmjd108.LostandFoundSys_2025.service;

import java.util.List;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;

public interface ItemService {
    void addItem(ItemDTO itemDTO);
    void deleteItem(String itemId);
    void updateItem(String itemId,ItemDTO itemDTO);
    ItemDTO getSelectedItem(String itemId);
    List<ItemDTO> getLostItems();
    List<ItemDTO> getFoundItems();
    List<ItemDTO> getClaimedItems();
}
