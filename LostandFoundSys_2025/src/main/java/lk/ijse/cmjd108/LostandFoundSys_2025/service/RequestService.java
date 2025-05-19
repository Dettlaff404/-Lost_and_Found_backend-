package lk.ijse.cmjd108.LostandFoundSys_2025.service;

import java.util.List;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;

public interface RequestService {
    void addRequest(RequestDTO requestDTO);
    void deleteRequest(String requestId);
    void updateRequest(String requestId, RequestDTO requestDTO);
    RequestDTO getSelectedRequest(String requestId);
    List<RequestDTO> getAllRequests();
}
