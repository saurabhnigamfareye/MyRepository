package com.example.PurchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by saurabh on 5/8/16.
 */

@Repository
public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder,Long> {


}
