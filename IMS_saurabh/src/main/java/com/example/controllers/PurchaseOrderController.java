package com.example.controllers;

import com.example.InventoryMaster.Dao;
import com.example.InventoryOrder.InventoryOrder;
import com.example.PurchaseOrder.PurchaseOrder;
import com.example.PurchaseOrder.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by saurabh on 4/8/16.
 */

@RestController
public class PurchaseOrderController {

    PurchaseOrderService purchaseOrderService;
    @Autowired
    public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    Dao dao;
    @Autowired
    public void setDao(Dao dao) {
        this.dao = dao;
    }



    @RequestMapping("/purchase")
    public PurchaseOrder purchase(@RequestBody PurchaseOrder purchaseOrder   )
    {
//        System.out.print(purchaseOrder.getTotalCost());

        purchaseOrder.setpODate(new Date());
        purchaseOrder.setCurrentTax(purchaseOrder.getTax());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        purchaseOrder.setUserName(username);


        if(purchaseOrderService.insert(purchaseOrder))
        {
            System.out.println("Inside innsert of purchase");
            Set<InventoryOrder> inventoryItems=purchaseOrder.getInventoryItems();
            Iterator<InventoryOrder> inventoryOrderIterator= inventoryItems.iterator();
            while (inventoryOrderIterator.hasNext()){
                if(!dao.update(inventoryOrderIterator.next()))
                {
                    System.out.println("Dao update Failed");
                    return null;
                }
            }
            System.out.println("Dao update successful");
            return purchaseOrder;
        }
        return null;

    }

    @RequestMapping("/purchaseDisplay")
    public List<PurchaseOrder> display()
    {

        System.out.println("In purchase Display");
        return purchaseOrderService.display();
    }


//    @RequestMapping("/productionHistoryDisplay")
//    public void displayH(@RequestParam("productName")String productName)
//    {
//
////                List<PurchaseOrder> list= purchaseOrderService.displayH(productName);
////
////        System.out.println(list.get(0));
////
////        return list;
//    }
}
