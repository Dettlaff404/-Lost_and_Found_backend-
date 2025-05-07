package lk.ijse.cmjd108.LostandFoundSys_2025.dto;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String email;
    private String mobile;
    private String password;
    private Role role;
}
