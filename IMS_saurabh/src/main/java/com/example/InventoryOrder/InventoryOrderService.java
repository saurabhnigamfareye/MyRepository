package com.example.InventoryOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by saurabh on 5/8/16.
 */

@Service
public class InventoryOrderService {

    InventoryOrderRepository inventoryOrderRepository;
    @Autowired
    public void setInventoryOrderRepository(InventoryOrderRepository inventoryOrderRepository) {
        this.inventoryOrderRepository = inventoryOrderRepository;
    }


    public boolean insert()
    {
        return true;

    }
}
