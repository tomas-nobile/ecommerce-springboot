package com.nobile.ecommerce.Purchase.Infraestructure.Persistance;

import org.springframework.data.repository.CrudRepository;

import com.nobile.ecommerce.Purchase.Domain.Purchase;


public interface PurchaseRepository extends CrudRepository<Purchase,Long>  {
    
}
