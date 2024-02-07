package com.nobile.ecommerce.Core.Application;

import com.nobile.ecommerce.User.Domain.User;

public interface UserServiceShared {
    User getUser(String email);
    User getUserLoggedIn();
} 