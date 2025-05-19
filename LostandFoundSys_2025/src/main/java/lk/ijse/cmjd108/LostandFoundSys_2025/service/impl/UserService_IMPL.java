package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.UserService;

@Service
public class UserService_IMPL implements UserService {

    @Override
    public void addUser(UserDTO userDTO) {
       System.out.println("User added Successfully");
       System.out.println(userDTO);
    }
    
    @Override
    public void deleteUser(String userId) {
        System.out.println("User deleted Successfully");
        System.out.println(userId);
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        System.out.println("User updated Successfully");
        System.out.println(userId);
        System.out.println(userDTO);
    }
    
    @Override
    public UserDTO getSelectedUser(String userId) {
        return new UserDTO();
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        return new ArrayList<UserDTO>();
    }

}
