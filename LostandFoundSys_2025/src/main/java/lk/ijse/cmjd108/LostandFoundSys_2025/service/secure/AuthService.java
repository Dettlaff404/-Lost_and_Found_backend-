package lk.ijse.cmjd108.LostandFoundSys_2025.service.secure;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.UserDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.secure.JWTAuthResponse;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
}
