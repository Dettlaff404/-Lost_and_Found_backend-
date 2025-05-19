package lk.ijse.cmjd108.LostandFoundSys_2025.service;

import java.util.List;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;

public interface UserService {
    void addUser();
    void updateUser();
    void deleteUser();
    UserDTO getSelectedUser();
    List<UserDTO> getAllUsers();
}
