package com.nobile.ecommerce.User.Infraestructure.Persistence;

import org.springframework.data.repository.CrudRepository;

import com.nobile.ecommerce.User.Domain.Profile;


public interface ProfileRepository extends CrudRepository<Profile,Long> {


}