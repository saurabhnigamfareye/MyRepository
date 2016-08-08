package com.example.Production;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by saurabh on 5/8/16.
 */
@Repository
public interface ProductionRepository extends JpaRepository<Production,Long> {

    public List<Production> findByProductionProductName(String productionProductName);
}
