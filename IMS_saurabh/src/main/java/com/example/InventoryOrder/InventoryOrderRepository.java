package com.example.InventoryOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by saurabh on 5/8/16.
 */

@Repository
public interface InventoryOrderRepository  extends JpaRepository<InventoryOrder,Long> {

public List<InventoryOrder> findByproductName(String productName);
}
