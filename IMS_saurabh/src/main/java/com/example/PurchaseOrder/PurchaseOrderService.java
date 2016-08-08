package com.example.PurchaseOrder;

import com.example.InventoryOrder.InventoryOrder;
import com.example.InventoryOrder.InventoryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by saurabh on 5/8/16.
 */

@Service
public class PurchaseOrderService {


    PurchaseOrderRepository purchaseOrderRepository;
    InventoryOrderRepository inventoryOrderRepository;
    @Autowired
    public void setInventoryOrderRepository(InventoryOrderRepository inventoryOrderRepository) {
        this.inventoryOrderRepository = inventoryOrderRepository;
    }




    @Autowired
    public void setPurchaseOrderRepository(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Transactional
    public boolean insert(PurchaseOrder purchaseOrder)
    {
        if(purchaseOrderRepository.save(purchaseOrder)!=null)
        {
            return true;
        }
        return false;
    }


    @Transactional
    public List<PurchaseOrder> display()
    {
        return purchaseOrderRepository.findAll();
    }


    @Transactional
    public void displayH(String productName) {

        List<InventoryOrder> inventoryOrders=inventoryOrderRepository.findByproductName(productName);
//        inventoryOrders.get(i).getPu

    }
}
