package com.nobile.ecommerce.Purchase.Application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nobile.ecommerce.Core.Infraestructure.Delivery.Response.CustomException.EntityNotFoundException;
import com.nobile.ecommerce.Purchase.Domain.Subscription;
import com.nobile.ecommerce.Purchase.Infraestructure.Persistance.SubscriptionRepository;
import com.nobile.ecommerce.User.Domain.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl {
    SubscriptionRepository subscriptionRepo;

    public Subscription getSubscription(Long subscriptionId) {
        return subscriptionExists(this.subscriptionRepo.findById(subscriptionId), subscriptionId);
    }

    private static Subscription subscriptionExists(Optional<Subscription> subscription, Long id) {
        if (subscription.isPresent()) {
            return subscription.get();
        } else {
            throw new EntityNotFoundException(id, User.class);
        }
    }
}
