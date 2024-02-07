package com.nobile.ecommerce.User.Infraestructure.Delivery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nobile.ecommerce.User.Application.UserService;
import com.nobile.ecommerce.User.Domain.Registration;
import com.nobile.ecommerce.User.Domain.User;

import jakarta.validation.Valid;
import lombok.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserRoute {
    UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<Registration> register(@Valid @RequestBody Registration registeredClient) {
        userService.register(registeredClient);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<User> me() {
        User user = userService.getUserLoggedIn();
        return ResponseEntity.ok().body(user);
    }
}
