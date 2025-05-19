package lk.ijse.cmjd108.LostandFoundSys_2025.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.dto.Status.ItemStatus;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.ItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("health")
    public String getItems() {
        return "Item Controller is working";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDTO) {
        itemService.addItem(itemDTO);
        System.out.println(itemDTO.getDescription());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteItem(@RequestParam ("itemId") String itemId) {
        System.out.println(itemId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@RequestParam ("itemId") String itemId,@RequestBody ItemDTO itemDTO) {
        System.err.println(itemId);
        System.out.println(itemDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDTO> getSelectedItem(@PathVariable String itemId) {
        System.out.println("Get Selected item for " + itemId);
        return ResponseEntity.ok(new ItemDTO(
            "ITEM-123", // itemId
            "USER-456", // claimedUserId
            "Placeholder Item Name", // itemName
            "This is a placeholder description", // description
            "Placeholder Location", // location
            LocalDate.now(), // date
            ItemStatus.CLAIMED // status
        ));        
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> itemsList = new ArrayList<>();

        itemsList.add(new ItemDTO(
            "ITEM-123", // itemId
            "USER-456", // claimedUserId
            "Placeholder Item Name", // itemName
            "This is a placeholder description", // description
            "Placeholder Location", // location
            LocalDate.now(), // date
            ItemStatus.CLAIMED // status
        ));

        itemsList.add(new ItemDTO(
            "ITEM-456", // itemId
            "USER-789", // claimedUserId
            "Placeholder Item Name", // itemName
            "This is a placeholder description", // description
            "Placeholder Location", // location
            LocalDate.now(), // date
            ItemStatus.CLAIMED // status
        ));

        itemsList.add(new ItemDTO(
            "ITEM-789", // itemId
            "USER-012", // claimedUserId
            "Placeholder Item Name", // itemName
            "This is a placeholder description", // description
            "Placeholder Location", // location
            LocalDate.now(), // date
            ItemStatus.CLAIMED // status
        ));

        return ResponseEntity.ok(itemsList);
    }

}
