package com.example.InventoryManagementSystem.Service;

import com.example.InventoryManagementSystem.Entity.InventoryItem;
import com.example.InventoryManagementSystem.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    // CREATE
    public InventoryItem addItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    // READ - ALL
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    // READ - BY ID
    public InventoryItem getItemById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    // UPDATE
    public InventoryItem updateItem(Integer id, InventoryItem updatedItem) {
        InventoryItem existingItem = getItemById(id);
        existingItem.setItemName(updatedItem.getItemName());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setQuantity(updatedItem.getQuantity());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setSupplier(updatedItem.getSupplier());

        return inventoryRepository.save(existingItem);
    }

    // DELETE
    public void deleteItem(Integer id) {
        InventoryItem item = getItemById(id);
        inventoryRepository.delete(item);
    }
}
