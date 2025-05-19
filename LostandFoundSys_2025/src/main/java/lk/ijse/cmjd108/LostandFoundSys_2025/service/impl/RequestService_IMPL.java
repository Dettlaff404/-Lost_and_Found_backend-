package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.RequestService;

@Service
public class RequestService_IMPL implements RequestService{
    
    @Override
    public void addRequest(RequestDTO requestDTO) {
        
    }
    
    @Override
    public void deleteRequest(String requestId) {
        
    }
    
    @Override
    public void updateRequest(String requestId, RequestDTO requestDTO) {
       
    }
    
    @Override
    public RequestDTO getSelectedRequest(String requestId) {
        return null;
    }
    
    @Override
    public List<RequestDTO> getAllRequests() {
        return List.of();
    }

}
