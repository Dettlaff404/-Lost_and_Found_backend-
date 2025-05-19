package lk.ijse.cmjd108.LostandFoundSys_2025.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String userId;
    private String userName;
    private String email;
    private String mobile;
    private String password;
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestEntity> requests;
    @OneToMany(mappedBy = "claimedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items;
}
