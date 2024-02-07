package com.nobile.ecommerce.Purchase.Infraestructure.Persistance;

import org.springframework.data.repository.CrudRepository;

import com.nobile.ecommerce.Purchase.Domain.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
    
}
