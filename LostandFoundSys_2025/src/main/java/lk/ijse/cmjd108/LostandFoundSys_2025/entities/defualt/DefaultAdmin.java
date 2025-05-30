package lk.ijse.cmjd108.LostandFoundSys_2025.entities.defualt;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.UserDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.Role;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.EntityDTO_Convertor;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.UtilData;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DefaultAdmin {
    
    private final UserDao userDao;
    private final EntityDTO_Convertor entityDTOConvertor;
    private final PasswordEncoder passwordEncoder;

    // value injections for defualt admin user
    @Value("${fullName}")
    private String fullName;

    @Value("${email}")
    private String email;

    @Value("${mobile}")
    private String mobile;

    @Value("${password}")
    private String password;

    /**
     * This method is called after the bean is created and initializes a default admin user
     * if it does not already exist in the database.
     */

    @PostConstruct
    public void addDefaultAdminUser() {
        UserDTO adminUser = new UserDTO();
        adminUser.setUserId(UtilData.generateUserId());
        adminUser.setFullname(fullName);
        adminUser.setEmail(email);
        adminUser.setMobile(mobile);
        adminUser.setPassword(passwordEncoder.encode(password));
        adminUser.setRole(Role.ADMIN);

        if (!(userDao.findByEmail(adminUser.getEmail()).isPresent())) {
            userDao.save(entityDTOConvertor.userDTOToUserEntity(adminUser));
        }
    }
}
