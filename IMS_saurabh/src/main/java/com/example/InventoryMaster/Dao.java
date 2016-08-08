package com.example.InventoryMaster;

import com.example.InventoryOrder.InventoryOrder;
import com.example.RawMaterial.RawMaterialsRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by saurabh on 4/8/16.
 */

@Service
public class Dao {



    InventoryRepository inventoryRepository;
    @Autowired
    public void setRepositoryInterface(InventoryRepository repositoryInterface) {
        this.inventoryRepository = repositoryInterface;
    }

    @Transactional
    public boolean insert(Inventory inventory)
    {
        if(inventoryRepository.save(inventory)!=null) {

            return true;
        }else
            return false;
    }

    @Transactional
    public List<Inventory> display()
    {
        return inventoryRepository.findAll();
    }

    @Transactional
    public List<Inventory> findById(int id)
    {
        return inventoryRepository.findByInventoryId(id);
    }

    @Transactional
    public boolean update(int id, String productName, double cost, int currentBalance, double dimension, String unitOfMeasurement)
    {   List<Inventory> a= inventoryRepository.findByInventoryId(id);
        if(a.size()!=0)
        {
            Inventory s= a.get(0);

            s.setProductName(productName);
            s.setCost(cost);
            s.setCurrentBalance(currentBalance);
            s.setDimension(dimension);
            s.setUnitOfMeasurement(unitOfMeasurement);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean delete(int id) {
        List<Inventory> a= inventoryRepository.findByInventoryId(id);

        if (a.size()!=0) {
            Inventory s = a.get(0);

            inventoryRepository.delete(s);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean update(InventoryOrder next) {


        String productName=next.getProductName();
        double cost = next.getCost();
        int quantity=next.getQuantity();
        System.out.println(productName+"productName");
        List<Inventory> inventories= inventoryRepository.findByProductName(productName);
        if(inventories.size()!=0)
        {
            Inventory inventory=inventories.get(0);

            inventory.setCost(cost);
            inventory.setCurrentBalance(inventory.getCurrentBalance()+quantity);
            return true;
        }


        return false;
    }
    @Transactional
    public boolean update(int totalManufacturedItem, String productionProductName) {

        List<Inventory> inventories= inventoryRepository.findByProductName(productionProductName);
        if(inventories.size()!=0)
        {
            Inventory inventory=inventories.get(0);
            System.out.println(inventory.getProductName());

            inventory.setCurrentBalance(inventory.getCurrentBalance()+totalManufacturedItem);
            return true;
        }
        return false;
    }


    @Transactional
    public boolean update(RawMaterialsRequired next) {


        String productName=next.getProductName();
        int quantity=next.getQuantity();


        List<Inventory> inventories= inventoryRepository.findByProductName(productName);
        if(inventories.size()!=0)
        {
            Inventory inventory=inventories.get(0);
            System.out.println(inventory.getProductName());

            inventory.setCurrentBalance(inventory.getCurrentBalance()-quantity);
            return true;
        }


        return false;
    }
}
