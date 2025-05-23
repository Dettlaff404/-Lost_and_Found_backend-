package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.RequestDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ReqStatus;
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
    private final EntityDTO_Convertor entityDTOConvertor;
    
    @Override
    public void addRequest(RequestDTO requestDTO) {
        requestDTO.setRequestId(UtilData.generateRequestId());
        requestDTO.setStatus(ReqStatus.PENDING);
        
        requestDao.save(entityDTOConvertor.requestDTOToRequestEntity(requestDTO));
        System.out.println("Request added Successfully");
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
        
        requestDao.save(entityDTOConvertor.requestDTOToRequestEntity(requestDTO));
        System.out.println("Request " + requestId + " updated Successfully");
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
