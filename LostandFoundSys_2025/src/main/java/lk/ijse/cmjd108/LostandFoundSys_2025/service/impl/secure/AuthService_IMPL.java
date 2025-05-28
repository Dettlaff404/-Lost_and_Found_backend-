package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl.secure;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd108.LostandFoundSys_2025.dao.UserDao;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.Role;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.secure.JWTAuthResponse;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.secure.SignIn;
import lk.ijse.cmjd108.LostandFoundSys_2025.entities.UserEntity;
import lk.ijse.cmjd108.LostandFoundSys_2025.security.jwt.JWTUtils;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.secure.AuthService;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.EntityDTO_Convertor;
import lk.ijse.cmjd108.LostandFoundSys_2025.util.UtilData;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService_IMPL implements AuthService{

    private final UserDao userDao;
    private final JWTUtils jwtUtils;
    private final EntityDTO_Convertor entityDTOConvert;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        UserEntity userByEmail = userDao.findByEmail(signIn.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        String generatedToken = jwtUtils.generateToken(userByEmail.getUserId(), userByEmail.getAuthorities());
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        userDTO.setUserId(UtilData.generateUserId());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRole(Role.USER);
        UserEntity savedUser = userDao.save(entityDTOConvert.userDTOToUserEntity(userDTO));
        String generatedToken = jwtUtils.generateToken(savedUser.getUserId(), savedUser.getAuthorities());
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

}
