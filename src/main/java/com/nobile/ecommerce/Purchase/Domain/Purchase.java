package com.nobile.ecommerce.Purchase.Domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nobile.ecommerce.User.Domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{validation.payment.dueDate.not_null}")
    @Column(nullable = false)
    private LocalDate dueDate;

    @NotNull(message = "{validation.payment.transaction.not_null}")
    @Column(nullable = false)
    private String transactionId;

    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    public Purchase(String transactionId, User user, Subscription subscription, LocalDate currDate , Integer addDays) {
        this.transactionId = transactionId;
        this.user = user;
        this.subscription = subscription;
        setDueDate(currDate,  addDays);
    }

    private void setDueDate(LocalDate currDate, Integer addDays) {
        this.dueDate= currDate.plusDays(addDays);
    }

}
