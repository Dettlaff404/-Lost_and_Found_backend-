package lk.ijse.cmjd108.LostandFoundSys_2025.util;

import java.util.UUID;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;

public class UtilData {
    
    public static String generateItemId(){
        return "ITM-" + UUID.randomUUID().toString();
    }

    public static String generateUserId(){
        return "USR-" + UUID.randomUUID().toString();
    }

    public static String generateRequestId(){
        return "REQ-" + UUID.randomUUID().toString();
    }

    public static ItemDTO reqToItem(RequestDTO requestDTO){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemId(generateItemId());
        itemDTO.setClaimedUserId("");
        itemDTO.setItemName(requestDTO.getItemName());
        itemDTO.setDescription(requestDTO.getDescription());
        itemDTO.setLocation(requestDTO.getLocation());
        itemDTO.setDate(requestDTO.getDate());
        itemDTO.setStatus(requestDTO.getItemStatus());
        return itemDTO;
    }

}
