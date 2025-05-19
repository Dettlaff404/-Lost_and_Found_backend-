package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.RequestService;

@Service
public class RequestService_IMPL implements RequestService{
    
    @Override
    public void addRequest(RequestDTO requestDTO) {
        System.out.println("Request added Successfully");
        System.out.println(requestDTO);
    }
    
    @Override
    public void deleteRequest(String requestId) {
        System.out.println("Request deleted Successfully");
        System.out.println(requestId);
    }
    
    @Override
    public void updateRequest(String requestId, RequestDTO requestDTO) {
       System.out.println("Request updated Successfully");
       System.out.println(requestId);
       System.out.println(requestDTO);
    }
    
    @Override
    public RequestDTO getSelectedRequest(String requestId) {
        return new RequestDTO();
    }
    
    @Override
    public List<RequestDTO> getAllRequests() {
        return new ArrayList<RequestDTO>();
    }

}
