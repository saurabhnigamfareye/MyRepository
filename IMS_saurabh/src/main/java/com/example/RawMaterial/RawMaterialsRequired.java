package com.example.RawMaterial;//package com.example.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by saurabh on 2/8/16.
 */
@Entity
public class RawMaterialsRequired {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int rawMaterialsId;
    String productName;
    int quantity;

    public int getRawMaterialsId() {
        return rawMaterialsId;
    }

    public void setRawMaterialsId(int rawMaterialsId) {
        this.rawMaterialsId = rawMaterialsId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
