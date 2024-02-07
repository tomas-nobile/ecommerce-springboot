package com.nobile.ecommerce.Purchase.Infraestructure.Delivery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nobile.ecommerce.Purchase.Application.PurchaseService;
import com.nobile.ecommerce.Purchase.Domain.Payment;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import jakarta.validation.Valid;
import lombok.*;

@AllArgsConstructor
@RestController
@RequestMapping("/buy")
public class PurchaseRoute {

        PurchaseService purchaseService;
    
    @PostMapping("/")
    public void  buy(@Valid @RequestBody Payment payment) throws StripeException {
        purchaseService.buy(payment);
    }

}
