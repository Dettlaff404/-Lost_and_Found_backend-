package lk.ijse.cmjd108.LostandFoundSys_2025.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.RequestNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.RequestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestDTO> addRequst(@RequestBody RequestDTO requestDTO){
        
        if (requestDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        RequestDTO response = requestService.addRequest(requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deleteRequest(@RequestParam ("requestId") String requestId){
        
        if (requestId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            requestService.deleteRequest(requestId);
            return ResponseEntity.noContent().build();
        } catch (RequestNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        
        
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<RequestDTO> updateRequest(@RequestParam String requestId, @RequestBody RequestDTO requestDTO){
        
        if (requestId.isEmpty() || requestDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            requestService.updateRequest(requestId, requestDTO);
            return ResponseEntity.noContent().build();
        } catch (RequestNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

    @GetMapping
    public ResponseEntity<RequestDTO> getSelectedRequest(@RequestParam String requestId) {
        
        if (requestId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(requestService.getSelectedRequest(requestId));
    }

    @GetMapping("/getallrequests")
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }
}
