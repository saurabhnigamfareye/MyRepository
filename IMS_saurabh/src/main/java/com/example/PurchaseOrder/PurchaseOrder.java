package com.example.PurchaseOrder;//package com.example.Domain;

import com.example.InventoryOrder.InventoryOrder;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by saurabh on 2/8/16.
 */
@Entity
public class PurchaseOrder {

    String nameOfVendor;
    Date pODate;

    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    Long pONumber;
    static double currentTax;
    double tax;
    double totalCost;
    double netCost;
//    User user;

    String userName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_order_pONumber")
    Set<InventoryOrder> inventoryItems;


    public Set<InventoryOrder> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(Set<InventoryOrder> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }



    public String getNameOfVendor() {
        return nameOfVendor;
    }

    public void setNameOfVendor(String nameOfVendor) {
        this.nameOfVendor = nameOfVendor;
    }

    public Date getpODate() {
        return pODate;
    }

    public void setpODate(Date pODate) {
        this.pODate = pODate;
    }

    public Long getpONumber() {
        return pONumber;
    }

    public void setpONumber(Long pONumber) {
        this.pONumber = pONumber;
    }

    public static double getCurrentTax() {
        return currentTax;
    }

    public static void setCurrentTax(double currentTax) {
        PurchaseOrder.currentTax = currentTax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        tax = tax;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getNetCost() {
        return netCost;
    }

    public void setNetCost(double netCost) {
        this.netCost = netCost;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
