package com.nobile.ecommerce.User.Application;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nobile.ecommerce.Core.Infraestructure.Delivery.Response.CustomException.EntityNotFoundException;
import com.nobile.ecommerce.User.Domain.Profile;
import com.nobile.ecommerce.User.Domain.Registration;
import com.nobile.ecommerce.User.Domain.User;
import com.nobile.ecommerce.User.Infraestructure.Persistence.ProfileRepository;
import com.nobile.ecommerce.User.Infraestructure.Persistence.UserRepository;
import com.nobile.ecommerce.User.Infraestructure.Security.Session;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userRepo;
    ProfileRepository profileRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;    

    public void register( Registration registeredClient) {
        User user = registeredClient.getUser();
        Profile profile = registeredClient.getProfile();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepo.save(user);
        profile.setUser(user);
        profileRepo.save(profile);
    }

    public User getUser(String email) {
        return userExists(this.userRepo.findByEmail(email), email);
    }

    public User getUserLoggedIn(){
        Session session = new Session();
        String email = session.getEmailLoggedIn();

        return this.getUser(email);
    }
    
    private static User userExists(Optional<User> user,String email) {
        if(user.isPresent()){
            return user.get();
        }else{
            throw new EntityNotFoundException(email, User.class);
        }
    }

}
