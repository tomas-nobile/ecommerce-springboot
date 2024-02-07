package com.nobile.ecommerce.Purchase.Application;

import org.springframework.stereotype.Service;

import com.nobile.ecommerce.Core.Application.UserServiceShared;
import com.nobile.ecommerce.Purchase.Domain.Payment;
import com.nobile.ecommerce.Purchase.Domain.Purchase;
import com.nobile.ecommerce.Purchase.Domain.Subscription;
import com.nobile.ecommerce.Purchase.Infraestructure.Persistance.PurchaseRepository;
import com.nobile.ecommerce.User.Domain.User;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    SubscriptionServiceImpl subscriptionService;
    UserServiceShared userService; 
    PurchaseRepository purchaseRepo;

    public void buy(Payment payment) throws StripeException {
        Subscription subscription = subscriptionService.getSubscription(payment.getSubscriptionId());
        User user = userService.getUserLoggedIn();
        String transactionId = pay(subscription, payment.getCreditCardToken(), user.getEmail()); 
        Purchase purchase = new Purchase(transactionId,user,subscription,payment.getCurrentDate(),subscription.getTermOnDays());
        purchaseRepo.save(purchase);
    }


    public String pay(Subscription subscription, String creditCardToken, String emailUser) throws StripeException {
        ChargeCreateParams params =
        ChargeCreateParams.builder()
            .setAmount((long) (subscription.getPrice() * 100))
            .setCurrency(subscription.getCurrency())
            .setSource("tok_visa")
            .setDescription(emailUser)
            .build();
        Charge charge = Charge.create(params);

        return charge.getId();
    }
}
