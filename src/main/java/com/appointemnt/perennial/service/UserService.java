package com.appointemnt.perennial.service;

import com.appointemnt.perennial.entity.User;

public interface UserService {
    public String registerUser(User user);
    public User authenticateUser(String userName,String password);
}
