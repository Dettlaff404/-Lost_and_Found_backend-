package lk.ijse.cmjd108.LostandFoundSys_2025.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private UserEntity claimedUser;
    private String itemName;
    private String description;
    private String location;
    private LocalDate date;
    private ItemStatus status;
}
