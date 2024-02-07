package com.nobile.ecommerce.Purchase.Domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "subscription")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{validation.subscription.name.not_blank}")
    @NotNull(message = "{validation.subscription.name.not_null}")
    @Size(min = 2, max = 20, message = "{validation.subscription.name.size}")
    @Column(nullable = false)
    private String name;

    @DecimalMin(value = "9", message = "{validation.subscription.priceUSD.min}")
    @DecimalMax(value = "90", message = "{validation.subscription.priceUSD.max}")
    @Column(nullable = false)
    private Double price;
    
    @NotNull(message = "{validation.subscription.termOnDays.not_null}")
    @Column(nullable = false)
    private String currency;

    @NotNull(message = "{validation.subscription.termOnDays.not_null}")
    @Column(nullable = false)
    @Min(value = 1, message = "{validation.subscription.termOnDays.min}")
    @Max(value = 365, message = "{validation.subscription.termOnDays.max}")
    private Integer TermOnDays;

    @OneToMany(mappedBy = "subscription")
    @JsonIgnore
    private List<Purchase> payments;
}
