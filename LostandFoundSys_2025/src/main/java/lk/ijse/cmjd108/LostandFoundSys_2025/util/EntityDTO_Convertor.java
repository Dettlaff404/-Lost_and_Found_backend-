package lk.ijse.cmjd108.LostandFoundSys_2025.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lk.ijse.cmjd108.LostandFoundSys_2025.dao.UserDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.ItemEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.RequestEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.UserEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.UserNotFoundException;

import org.modelmapper.TypeToken;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntityDTO_Convertor {

    private final ModelMapper modelMapper;
    private final UserDao userDao;

    //user
    public UserDTO userEntityToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public UserEntity userDTOToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public List<UserDTO> toUserDTOsList(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, new TypeToken<List<UserDTO>>(){}.getType());
    }

    //request
    public RequestDTO requestEntityToRequestDTO(RequestEntity requestEntity) {
        try {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setRequestId(requestEntity.getRequestId());
            requestDTO.setUserId(requestEntity.getUser().getUserId());   
            requestDTO.setItemName(requestEntity.getItemName());
            requestDTO.setDescription(requestEntity.getDescription());
            requestDTO.setLocation(requestEntity.getLocation());
            requestDTO.setDate(requestEntity.getDate());
            requestDTO.setItemStatus(requestEntity.getItemStatus());
            requestDTO.setStatus(requestEntity.getStatus());
            return requestDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public RequestEntity requestDTOToRequestEntity(RequestDTO requestDTO) {
        try {
            RequestEntity requestEntity = new RequestEntity();
            requestEntity.setRequestId(requestDTO.getRequestId());
            requestEntity.setUser(userDao.findById(requestDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found")));
            requestEntity.setItemName(requestDTO.getItemName());
            requestEntity.setDescription(requestDTO.getDescription());
            requestEntity.setLocation(requestDTO.getLocation());
            requestEntity.setDate(requestDTO.getDate());
            requestEntity.setItemStatus(requestDTO.getItemStatus());
            requestEntity.setStatus(requestDTO.getStatus());
        return requestEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public List<RequestDTO> toRequestDTOsList(List<RequestEntity> requestEntities) {
        return requestEntities.stream().map(requestEntity -> 
            requestEntityToRequestDTO(requestEntity)
        ).collect(Collectors.toList());
    }

    //item
    public ItemDTO itemEntityToItemDTO(ItemEntity itemEntity) {
        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemId(itemEntity.getItemId());
            if (itemEntity.getClaimedUser() == null) {
                itemDTO.setClaimedUserId("");
            }else {
                itemDTO.setClaimedUserId(itemEntity.getClaimedUser().getUserId());
            }
            itemDTO.setItemName(itemEntity.getItemName());
            itemDTO.setDescription(itemEntity.getDescription());
            itemDTO.setLocation(itemEntity.getLocation());
            itemDTO.setDate(itemEntity.getDate());
            itemDTO.setStatus(itemEntity.getStatus());
            return itemDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ItemEntity itemDTOToItemEntity(ItemDTO itemDTO) {
        try {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setItemId(itemDTO.getItemId());
            if (itemDTO.getClaimedUserId().equals("") || itemDTO.getClaimedUserId() == null) {
                itemEntity.setClaimedUser(null);
            }else {
                itemEntity.setClaimedUser(userDao.findById(itemDTO.getClaimedUserId()).orElseThrow(() -> new UserNotFoundException("User not found")));
            }
            itemEntity.setItemName(itemDTO.getItemName());
            itemEntity.setDescription(itemDTO.getDescription());
            itemEntity.setLocation(itemDTO.getLocation());
            itemEntity.setDate(itemDTO.getDate());
            itemEntity.setStatus(itemDTO.getStatus());
            return itemEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<ItemDTO> toItemDTOsList(List<ItemEntity> itemEntities) {
        return itemEntities.stream().map(itemEntity -> 
            itemEntityToItemDTO(itemEntity)
        ).collect(Collectors.toList());
    }

}
