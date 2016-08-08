package com.example.InventoryOrder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by saurabh on 2/8/16.
 */


@Entity
public class InventoryOrder {


    //    @OneToOne
//   Inventory inventory;


    @Id
    @GeneratedValue
    Long inventoryOrderId;


    String productName;
    int quantity;
    double cost;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "purchase_order_pONumber")
//    PurchaseOrder purchaseOrder;
//
//    public PurchaseOrder getPurchaseOrder() {
//        return purchaseOrder;
//    }
//
//    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
//        this.purchaseOrder = purchaseOrder;
//    }

    public Long getInventoryOrderId() {
        return inventoryOrderId;
    }

    public void setInventoryOrderId(Long inventoryOrderId) {
        this.inventoryOrderId = inventoryOrderId;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



}
