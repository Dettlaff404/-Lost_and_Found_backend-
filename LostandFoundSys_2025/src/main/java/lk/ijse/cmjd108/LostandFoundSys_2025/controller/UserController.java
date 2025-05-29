package lk.ijse.cmjd108.LostandFoundSys_2025.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.UserNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        
        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserDTO response = userService.addUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam String userId){

        if (userId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);   
        }
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestParam String userId, @RequestBody UserDTO userDto){
        
        if (userId.isEmpty() || userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        try {
            userService.updateUser(userId, userDto);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @GetMapping
    public ResponseEntity<UserDTO> getSelectedUser(@RequestParam String userId) {
        
        if (userId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        try {
            return ResponseEntity.ok(userService.getSelectedUser(userId));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @GetMapping("/getuserbyemail")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        
        if (email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        try {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
