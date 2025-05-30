package lk.ijse.cmjd108.LostandFoundSys_2025.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lk.ijse.cmjd108.LostandFoundSys_2025.dao.RequestDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.UserDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.ItemEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.RequestEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.UserEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.RequestNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.UserNotFoundException;

import org.modelmapper.TypeToken;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntityDTO_Convertor {

    private final ModelMapper modelMapper;
    private final UserDao userDao;
    private final RequestDao requestDao;

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
    
    // Helper method to get user by ID
    public UserEntity getUserById(String userId) {
        return userDao.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
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
            System.err.println("Error converting RequestEntity to RequestDTO: " + e.getMessage());
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
            System.err.println("Error converting RequestDTO to RequestEntity: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<RequestDTO> toRequestDTOsList(List<RequestEntity> requestEntities) {
        return requestEntities.stream()
            .map(this::requestEntityToRequestDTO)
            .filter(dto -> dto != null)
            .collect(Collectors.toList());
    }

    //item
    public ItemDTO itemEntityToItemDTO(ItemEntity itemEntity) {
        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemId(itemEntity.getItemId());
            
            if (itemEntity.getRequestEntity() != null) {
                itemDTO.setRequestId(itemEntity.getRequestEntity().getRequestId());
            }
            
            if (itemEntity.getClaimedUser() == null) {
                itemDTO.setClaimedUserId("");
            } else {
                itemDTO.setClaimedUserId(itemEntity.getClaimedUser().getUserId());
            }
            
            itemDTO.setItemName(itemEntity.getItemName());
            itemDTO.setDescription(itemEntity.getDescription());
            itemDTO.setLocation(itemEntity.getLocation());
            itemDTO.setDate(itemEntity.getDate());
            itemDTO.setStatus(itemEntity.getStatus());
            return itemDTO;
        } catch (Exception e) {
            System.err.println("Error converting ItemEntity to ItemDTO: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public ItemEntity itemDTOToItemEntity(ItemDTO itemDTO) {
        try {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setItemId(itemDTO.getItemId());
            
            if (itemDTO.getRequestId() != null && !itemDTO.getRequestId().isEmpty()) {
                Optional<RequestEntity> requestEntity = requestDao.findById(itemDTO.getRequestId());
                if (requestEntity.isPresent()) {
                    itemEntity.setRequestEntity(requestEntity.get());
                } else {
                    throw new RequestNotFoundException("Request not found with ID: " + itemDTO.getRequestId());
                }
            }
            
            if (itemDTO.getClaimedUserId() != null && !itemDTO.getClaimedUserId().isEmpty()) {
                Optional<UserEntity> userEntity = userDao.findById(itemDTO.getClaimedUserId());
                if (userEntity.isPresent()) {
                    itemEntity.setClaimedUser(userEntity.get());
                } else {
                    System.err.println("Warning: User not found with ID: " + itemDTO.getClaimedUserId());
                    itemEntity.setClaimedUser(null);
                }
            } else {
                itemEntity.setClaimedUser(null);
            }
            
            itemEntity.setItemName(itemDTO.getItemName());
            itemEntity.setDescription(itemDTO.getDescription());
            itemEntity.setLocation(itemDTO.getLocation());
            itemEntity.setDate(itemDTO.getDate());
            itemEntity.setStatus(itemDTO.getStatus());

            return itemEntity;
        } catch (Exception e) {
            System.err.println("Error converting ItemDTO to ItemEntity: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ItemDTO> toItemDTOsList(List<ItemEntity> itemEntities) {
        return itemEntities.stream()
            .map(this::itemEntityToItemDTO)
            .filter(dto -> dto != null)
            .collect(Collectors.toList());
    }
}