package com.nobile.ecommerce.User.Application;

import com.nobile.ecommerce.Core.Application.UserServiceShared;
import com.nobile.ecommerce.User.Domain.Registration;

public interface UserService extends UserServiceShared {
    void register (Registration registration);
}
