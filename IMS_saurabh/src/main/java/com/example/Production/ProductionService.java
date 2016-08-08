package com.example.Production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by saurabh on 5/8/16.
 */

@Service
public class ProductionService {


    ProductionRepository productionRepository;
    @Autowired
    public void setProductionRepository(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Transactional
    public boolean insert(Production production)
    {
        if(productionRepository.save(production)!=null)
        {
            return true;
        }
        return false;
    }


    @Transactional
    public List<Production> display()
    {
        return productionRepository.findAll();
    }

    @Transactional
    public List<Production> displayH(String productionProductName) {
        return productionRepository.findByProductionProductName(productionProductName);
    }
}
