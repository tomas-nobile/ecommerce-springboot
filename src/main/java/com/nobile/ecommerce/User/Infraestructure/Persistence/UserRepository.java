package com.nobile.ecommerce.User.Infraestructure.Persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nobile.ecommerce.User.Domain.User;

public interface UserRepository extends  CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);

}