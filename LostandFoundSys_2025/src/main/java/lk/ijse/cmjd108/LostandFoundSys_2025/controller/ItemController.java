package lk.ijse.cmjd108.LostandFoundSys_2025.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.cmjd108.LostandFoundSys_2025.dto.ItemDTO;
import lk.ijse.cmjd108.LostandFoundSys_2025.exception.ItemNotFoundException;
import lk.ijse.cmjd108.LostandFoundSys_2025.service.ItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDTO) {
        
    //     if (itemDTO == null) {
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     }
        
    //     itemService.addItem(itemDTO);
    //     return new ResponseEntity<>(HttpStatus.CREATED);
    // }

    @DeleteMapping
    public ResponseEntity<Void> deleteItem(@RequestParam ("itemId") String itemId) {
        
        if (itemId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            itemService.deleteItem(itemId);
            return ResponseEntity.noContent().build();
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        
        
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@RequestParam ("itemId") String itemId,@RequestBody ItemDTO itemDTO) {
        
        if (itemId.isEmpty() || itemDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            itemService.updateItem(itemId, itemDTO);
            return ResponseEntity.noContent().build();
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }

        
    }

    @GetMapping
    public ResponseEntity<ItemDTO> getSelectedItem(@RequestParam String itemId) {
        
        if (itemId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            return ResponseEntity.ok(itemService.getSelectedItem(itemId)); 
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        } 
    }

    @GetMapping("/getallitems")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

}
