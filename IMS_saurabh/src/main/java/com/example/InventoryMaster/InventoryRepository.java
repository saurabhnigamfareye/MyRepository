package com.example.InventoryMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by saurabh on 4/8/16.
 */


@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    public List<Inventory> findByInventoryId(int inventoryId);
   public List<Inventory> findByProductName(String productName);
}
