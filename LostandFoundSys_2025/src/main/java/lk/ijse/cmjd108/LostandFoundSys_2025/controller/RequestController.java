package lk.ijse.cmjd108.LostandFoundSys_2025.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.RequestDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.RequestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping("health")
    public String healthCheck() {
        return "Request Controller is working";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRequst(@RequestBody RequestDTO requestDTO){
        requestService.addRequest(requestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRequest(@RequestParam ("requestId") String requestId){
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateRequest(@RequestParam String requestId, @RequestBody RequestDTO requestDTO){
        requestService.updateRequest(requestId, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<RequestDTO> getSelectedRequest(@PathVariable String requestId) {
        return ResponseEntity.ok(requestService.getSelectedRequest(requestId));
    }

    @GetMapping
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }
}
