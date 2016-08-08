package com.example.InventoryMaster;

import javax.persistence.*;

/**
 * Created by saurabh on 4/8/16.
 */
@Entity
public class Inventory {


    @Id
    @GeneratedValue
    int inventoryId;
    @Column(unique = true,nullable = false)
    String productName;

    double cost;
    String unitOfMeasurement;
    final int openingBalance;
    double dimension;
    int currentBalance;

    public Inventory(String productName, double cost, String unitOfMeasurement, int openingBalance, double dimension) {

        this.productName=productName;
        this.cost=cost;
        this.unitOfMeasurement=unitOfMeasurement;
        this.openingBalance=openingBalance;
        this.dimension=dimension;
        this.currentBalance=openingBalance;
    }
    public Inventory()
    {
        openingBalance=0;
        currentBalance=openingBalance;
    }
    public Inventory(String productName)
    {
        this.productName=productName;
        openingBalance=0;
        currentBalance=openingBalance;
    }
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getOpeningBalance() {
        return openingBalance;
    }



    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Inventory(int openingBalance) {

        this.openingBalance = openingBalance;
    }

}
