package com.example.InventoryManagementSystem.Controller;

import com.example.InventoryManagementSystem.Entity.InventoryItem;
import com.example.InventoryManagementSystem.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/v1")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // CREATE
    @PostMapping("/addItem")
    public ResponseEntity<InventoryItem> addItem(@RequestBody InventoryItem item) {
        return ResponseEntity.ok(inventoryService.addItem(item));
    }

    // READ - ALL
    @GetMapping("/getAll")
    public ResponseEntity<List<InventoryItem>> getAllItems() {
        return ResponseEntity.ok(inventoryService.getAllItems());
    }

    // READ - BY ID
    @GetMapping("/get/{id}")
    public ResponseEntity<InventoryItem> getItemById(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getItemById(id));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<InventoryItem> updateItem(
            @PathVariable Integer id,
            @RequestBody InventoryItem item) {
        return ResponseEntity.ok(inventoryService.updateItem(id, item));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.ok("Inventory item deleted successfully");
    }
}
