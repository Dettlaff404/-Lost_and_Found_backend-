package lk.ijse.cmjd108.LostandFoundSys_2025.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private String itemId;
    @OneToOne
    @JoinColumn(name = "requestId", nullable = false)
    private String requestId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private UserEntity claimedUser;
    private String itemName;
    private String description;
    private String location;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
}
