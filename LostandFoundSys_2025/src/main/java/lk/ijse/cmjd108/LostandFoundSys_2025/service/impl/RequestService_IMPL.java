package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.ItemDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.RequestDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ReqStatus;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.ItemEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.RequestEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.RequestNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.RequestService;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.EntityDTO_Convertor;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.UtilData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestService_IMPL implements RequestService{

    private final RequestDao requestDao;
    private final ItemDao itemDao;
    private final EntityDTO_Convertor entityDTOConvertor;
    
    @Override
    public RequestDTO addRequest(RequestDTO requestDTO) {
        requestDTO.setRequestId(UtilData.generateRequestId());
        requestDTO.setStatus(ReqStatus.PENDING);
        
        requestDao.save(entityDTOConvertor.requestDTOToRequestEntity(requestDTO));
        System.out.println("Request added Successfully");
        return requestDTO;
    }
    
    @Override
    public void deleteRequest(String requestId) {

        Optional<RequestEntity> foundRequestEntity = requestDao.findById(requestId);
        if (foundRequestEntity.isEmpty()) {
            throw new RequestNotFoundException("Request not found");
        }

        requestDao.deleteById(requestId);
        System.out.println("Request " + requestId + " deleted Successfully");
    }
    
    @Override
    public void updateRequest(String requestId, RequestDTO requestDTO) {

        Optional<RequestEntity> foundRequestEntity = requestDao.findById(requestId);
        if (foundRequestEntity.isEmpty()) {
            throw new RequestNotFoundException("Request not found");
        }

        RequestEntity requestEntity = foundRequestEntity.get();
        ReqStatus oldStatus = requestEntity.getStatus();
        
        requestEntity.setItemName(requestDTO.getItemName());
        requestEntity.setDescription(requestDTO.getDescription());
        requestEntity.setLocation(requestDTO.getLocation());
        requestEntity.setDate(requestDTO.getDate());
        requestEntity.setItemStatus(requestDTO.getItemStatus());
        requestEntity.setStatus(requestDTO.getStatus());

        if (requestDTO.getStatus() == ReqStatus.APPROVED) {
            handleApprovedRequest(requestEntity, requestDTO);
        } else if (oldStatus == ReqStatus.APPROVED && requestDTO.getStatus() != ReqStatus.APPROVED) {
            handleRejectedRequest(requestId);
        }
        
        requestDao.save(requestEntity);
        System.out.println("Request " + requestId + " updated Successfully");
    }
    
    private void handleApprovedRequest(RequestEntity requestEntity, RequestDTO requestDTO) {
        ItemEntity existingItem = requestEntity.getItem();
        
        if (existingItem != null) {
            existingItem.setItemName(requestDTO.getItemName());
            existingItem.setDescription(requestDTO.getDescription());
            existingItem.setLocation(requestDTO.getLocation());
            existingItem.setDate(requestDTO.getDate());
            existingItem.setStatus(requestDTO.getItemStatus());
            itemDao.save(existingItem);
        } else {
            ItemEntity newItem = entityDTOConvertor.itemDTOToItemEntity(UtilData.reqToItem(requestDTO));
            
            requestEntity.setItem(newItem);
            
            itemDao.save(newItem);
        }
    }
    
    private void handleRejectedRequest(String requestId) {
        Optional<RequestEntity> requestEntity = requestDao.findById(requestId);
        if (requestEntity.isPresent() && requestEntity.get().getItem() != null) {
            requestEntity.get().setItem(null);
            requestDao.save(requestEntity.get());
        }
    }
    
    @Override
    public RequestDTO getSelectedRequest(String requestId) {
        
        Optional<RequestEntity> foundRequestEntity = requestDao.findById(requestId);
        if (foundRequestEntity.isEmpty()) {
            throw new RequestNotFoundException("Selected Request not found");
        }
        
        return entityDTOConvertor.requestEntityToRequestDTO(foundRequestEntity.get());

    }
    
    @Override
    public List<RequestDTO> getAllRequests() {
        return entityDTOConvertor.toRequestDTOsList(requestDao.findAll());
    }

}