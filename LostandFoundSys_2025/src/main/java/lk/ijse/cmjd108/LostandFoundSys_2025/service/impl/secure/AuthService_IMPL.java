package lk.ijse.cmjd108.LostandFoundSys_2025.service.impl.secure;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.secure.JWTAuthResponse;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.secure.SignIn;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.secure.AuthService;

@Service
@Transactional
public class AuthService_IMPL implements AuthService{

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        return null;
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        return null;
    }

}
