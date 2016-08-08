package com.example.controllers;

import com.example.InventoryMaster.Dao;
import com.example.InventoryMaster.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by saurabh on 4/8/16.
 */

@RestController
public class IndexController {

    @Autowired
    Dao myDao;


//   > public void setMyDao(Dao myDao) {
//        this.myDao = myDao;
//    }

    @RequestMapping(value = "display")
    public List<Inventory> display()
    {
        return myDao.display();
    }

    @RequestMapping(value = "/create")
    public Inventory create(@RequestParam("productName") String productName,
                            @RequestParam("cost") String cost,
                            @RequestParam("unitOfMeasurement") String unitOfMeasurement,
                            @RequestParam("openingBalance") String openingBalance,
                            @RequestParam("dimension") String dimension)
    {

        double costParsed=Double.parseDouble(cost);
        int openingBalanceParsed=Integer.parseInt(openingBalance);
        double dimensionParsed=Double.parseDouble(dimension);
        Inventory inventory= new Inventory(productName,costParsed,unitOfMeasurement,openingBalanceParsed,dimensionParsed);


        if(myDao.insert(inventory))
        {
            return inventory;
        }
        return null;
    }

    @RequestMapping("delete")
    public Inventory delete(@RequestParam("inventoryId") String inventoryId)
    {

        int inventoryIdParsed=Integer.parseInt(inventoryId);
        if(myDao.delete(inventoryIdParsed))
        {
            return new Inventory("Success");
        }
        else
        {
            return null;
        }
    }

    @RequestMapping("update")
    public Inventory update(@RequestParam("inventoryId") String inventoryId ,
                            @RequestParam("productName") String productName,
                            @RequestParam("cost") String cost,
                            @RequestParam("unitOfMeasurement") String unitOfMeasurement,
                            @RequestParam("currentBalance") String currentBalance,
                            @RequestParam("dimension") String dimension)
    {
        double costParsed=Double.parseDouble(cost);
        int currentBalanceParsed=Integer.parseInt(currentBalance);
        double dimensionParsed=Double.parseDouble(dimension);
        int inventoryIdParsed=Integer.parseInt(inventoryId);

        if(myDao.update(inventoryIdParsed,productName,costParsed,currentBalanceParsed,dimensionParsed,unitOfMeasurement))
        {
            System.out.print("Inside Update if");
            return new Inventory("successful");
        }
        else
        {
            System.out.print("Inside Update else");
            return null;
        }

    }

}
