package lk.ijse.cmjd108.LostandFoundSys_2025.entities.defualt;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.UserDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.Role;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.EntityDTO_Convertor;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.UtilData;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class addDefaultAdminUser {

    private final UserDao userDao;
    private final EntityDTO_Convertor entityDTOConvertor;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void addDefaultAdminUser() {
        UserDTO adminUser = new UserDTO();
        adminUser.setUserId(UtilData.generateUserId());
        adminUser.setFullname("Admin");
        adminUser.setEmail("admin@mail.com");
        adminUser.setMobile("0123456789");
        adminUser.setPassword(passwordEncoder.encode("Admin2025@Lost&found"));
        adminUser.setRole(Role.ADMIN);

        if (!(userDao.findByEmail(adminUser.getEmail()).isPresent())) {
            userDao.save(entityDTOConvertor.userDTOToUserEntity(adminUser));
        }
    }

}
