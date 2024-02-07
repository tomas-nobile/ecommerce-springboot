package com.nobile.ecommerce.Purchase.Infraestructure.Gateway;

import org.springframework.stereotype.Service;

import com.nobile.ecommerce.Purchase.Domain.Subscription;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Stripe {

    public String pay(Subscription subscription, String creditCardToken, String emailUser) throws StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) (subscription.getPrice() * 100))
                .setCurrency(subscription.getCurrency())
                .setSource("tok_visa")
                .setDescription(emailUser)
                .build();
        Charge charge = Charge.create(params);

        return charge.getId();
    }
}
