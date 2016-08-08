package com.example.controllers;

import com.example.InventoryMaster.Dao;
import com.example.Production.Production;
import com.example.Production.ProductionService;
import com.example.RawMaterial.RawMaterialsRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by saurabh on 5/8/16.
 */


@RestController
public class ProductionController {


    ProductionService productionService;
    @Autowired
    public void setProductionService(ProductionService productionService) {
        this.productionService = productionService;
    }

    Dao dao;
    @Autowired
    public void setDao(Dao dao) {
        this.dao = dao;
    }



    @RequestMapping("/production")
    public Production purchase(@RequestBody Production production) {


        production.setProductionDate(new Date());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        production.setUserName(username);

        if (productionService.insert(production)) {

            if (!dao.update(production.getTotalManufacturedItem(), production.getProductionProductName())) {
                return null;
            }
            Set<RawMaterialsRequired> rawMaterials = production.getRawMaterials();

            Iterator<RawMaterialsRequired> rawMaterialsRequiredIterator = rawMaterials.iterator();
            while (rawMaterialsRequiredIterator.hasNext()) {
                if(!dao.update(rawMaterialsRequiredIterator.next()))
                {
                    System.out.println("Dao update Failed");
                    return null;
                }
            }

            }
            return production;

        }



    @RequestMapping("/productionDisplay")
    public List<Production> display()
    {

        System.out.println("In purchase Display");
        return productionService.display();
    }


    @RequestMapping("/productionHistoryDisplay")
    public List<Production> displayH(@RequestParam("productionProductName")String productionProductName)
    {

        System.out.println("In purchase history Display"+productionProductName);


        List<Production> list= productionService.displayH(productionProductName);

        System.out.println(list.get(0));

        return list;
    }





}
