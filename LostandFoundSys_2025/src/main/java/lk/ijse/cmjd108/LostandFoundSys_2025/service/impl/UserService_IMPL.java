package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.UserDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.UserEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.UserNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.UserService;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.EntityDTO_Convertor;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.UtilData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService_IMPL implements UserService {

    private final UserDao userDao;
    private final EntityDTO_Convertor entityDTOConvertor;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDTO userDTO) {
        userDTO.setUserId(UtilData.generateUserId());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        userDao.save(entityDTOConvertor.userDTOToUserEntity(userDTO));

        System.out.println("User added Successfully");
    }
    
    @Override
    public void deleteUser(String userId) {

        Optional<UserEntity> foundUserEntity = userDao.findById(userId);
        if (foundUserEntity.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        
        userDao.deleteById(userId);
        System.out.println("User " + userId + " deleted Successfully");
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {

        Optional<UserEntity> foundUserEntity = userDao.findById(userId);
        if (foundUserEntity.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (passwordEncoder.encode(userDTO.getPassword()) != foundUserEntity.get().getPassword()) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        
        userDao.save(entityDTOConvertor.userDTOToUserEntity(userDTO));
        System.out.println("User " + userId + " updated Successfully");
    }
    
    @Override
    public UserDTO getSelectedUser(String userId) {
        
        Optional<UserEntity> foundUserEntity = userDao.findById(userId);
        if (foundUserEntity.isEmpty()) {
            throw new UserNotFoundException("Selected User not found");
        }
        
        return entityDTOConvertor.userEntityToUserDTO(foundUserEntity.get());
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        return entityDTOConvertor.toUserDTOsList(userDao.findAll());
    }

}
