package lk.ijse.cmjd108.LostandFoundSys_2025.service;

import java.util.List;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;

public interface ItemService {
    void addItem();
    void deleteItem();
    void updateItem();
    ItemDTO getSelectedItem();
    List<ItemDTO> getAllItems();
}
