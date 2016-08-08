package com.example.Production;//package com.example.Domain;

import com.example.RawMaterial.RawMaterialsRequired;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by saurabh on 2/8/16.
 */

//a.     Item of productions
//
//        b.     Production date
//
//        c.     Production Number
//
//        d.     Raw Materials (Inventory items) required
//
//        i.     Item name 1, Quantity required
//
//        ii.     Item name 2, Quantity required
//
//        iii.     … upto n
//
//        e.     Total manufactured item


@Entity
public class Production {
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "production_production_number")
//    Inventory producedInventory;

//    User user;


    String userName;
    String productionProductName;
    Date productionDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long productionNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="production_production_number")
    Set<RawMaterialsRequired> rawMaterials;
    int totalManufacturedItem;

    public String getProductionProductName() {
        return productionProductName;
    }

    public void setProductionProductName(String productionProductName) {
        this.productionProductName = productionProductName;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Long getProductionNumber() {
        return productionNumber;
    }

    public void setProductionNumber(Long productionNumber) {
        this.productionNumber = productionNumber;
    }

    public Set<RawMaterialsRequired> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(Set<RawMaterialsRequired> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    public int getTotalManufacturedItem() {
        return totalManufacturedItem;
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

    public void setTotalManufacturedItem(int totalManufacturedItem) {
        this.totalManufacturedItem = totalManufacturedItem;
    }
}
