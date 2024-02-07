package com.nobile.ecommerce.User.Domain;

import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nobile.ecommerce.Purchase.Domain.Purchase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Email(message = "{validation.user.email}")
  @NotBlank(message = "{validation.user.email.not_blank}")
  @NotNull(message = "{validation.user.email.not_null}")
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NotBlank(message = "{validation.user.password.not_blank}")
  @NotNull(message = "{validation.user.password.not_null}")
  @Size(min = 6, message = "{validation.user.password.size.too_short}")
  @Column(nullable = false)
  private String password;

  @OneToOne( mappedBy = "user")
  private Profile profile;

  @OneToMany(mappedBy = "user")
  private List<Purchase> payments;



}
