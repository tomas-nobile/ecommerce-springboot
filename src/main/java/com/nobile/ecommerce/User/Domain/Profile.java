package com.nobile.ecommerce.User.Domain;

import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{validation.profile.firstName.not_blank}")
    @NotNull(message = "{validation.profile.firstName.not_null}")
    @Size(min = 2, message = "{validation.profile.firstName.size.too_short}")
    @Size(max = 20, message = "{validation.profile.firstName.size.too_long}")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "{validation.profile.lastName.not_blank}")
    @NotNull(message = "{validation.profile.lastName.not_null}")
    @Size(min = 2, message = "{validation.profile.lastName.size.too_short}")
    @Size(max = 20, message = "{validation.profile.lastName.size.too_long}")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "{validation.profile.newsletter.not_null}")
    @Column(nullable = false)
    private Boolean newsletter;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}