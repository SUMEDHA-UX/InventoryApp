package com.example.InventoryManagementSystem.Repository;

import com.example.InventoryManagementSystem.Entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Integer> {

}
