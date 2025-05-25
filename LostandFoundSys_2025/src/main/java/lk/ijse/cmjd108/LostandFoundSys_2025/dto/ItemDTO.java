package lk.ijse.cmjd108.LostandFoundSys_2025.dto;

import java.time.LocalDate;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String itemId;
    private String requestId;
    private String claimedUserId;
    private String itemName;
    private String description;
    private String location;
    private LocalDate date;
    private ItemStatus status;
}
