package lk.ijse.cmjd108.LostandFoundSys_2025.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ItemStatus;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ReqStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "request")
public class RequestEntity {
    @Id
    @OneToOne(mappedBy = "requestId", cascade = CascadeType.ALL, orphanRemoval = true)
    private String requestId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
    private String itemName;
    private String description;
    private String location;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
    @Enumerated(EnumType.STRING)
    private ReqStatus status;

}
