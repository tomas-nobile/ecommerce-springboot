package com.nobile.ecommerce.Purchase.Application;

import com.nobile.ecommerce.Purchase.Domain.Payment;
import com.stripe.exception.StripeException;

public interface PurchaseService {
    void buy(Payment payment) throws StripeException;
}
