package lk.ijse.cmjd108.LostandFoundSys_2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthTest {

    @GetMapping
    public String healthCheck() {
        return "Lost and Found System is up and running...";
    }
    
}
