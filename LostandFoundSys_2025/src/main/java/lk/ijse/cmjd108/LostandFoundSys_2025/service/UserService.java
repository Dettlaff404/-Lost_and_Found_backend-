package lk.ijse.cmjd108.LostandFoundSys_2025.service;

import java.util.List;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    void updateUser(String userId, UserDTO userDTO);
    void deleteUser(String userId);
    UserDTO getSelectedUser(String userId);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUsers();
}
