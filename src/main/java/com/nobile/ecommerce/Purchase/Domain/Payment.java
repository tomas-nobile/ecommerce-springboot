package com.nobile.ecommerce.Purchase.Domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    LocalDate currentDate = LocalDate.now();
    Long subscriptionId;
    String creditCardToken;

        public Payment(Long subscriptionId, String creditCardToken) {
        this.subscriptionId = subscriptionId;
        this.creditCardToken = creditCardToken;
    }
}
