package com.nobile.ecommerce.User.Domain;

import jakarta.validation.Valid;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    @Valid
    private User user;

    @Valid
    private Profile profile;

}