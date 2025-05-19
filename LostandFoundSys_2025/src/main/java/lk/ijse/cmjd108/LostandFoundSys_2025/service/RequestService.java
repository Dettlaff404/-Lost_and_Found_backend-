package lk.ijse.cmjd108.LostandFoundSys_2025.service;

import java.util.List;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;

public interface RequestService {
    void addRequest();
    void deleteRequest();
    void updateRequest();
    RequestDTO getSelectedRequest();
    List<RequestDTO> getAllRequests();
}
