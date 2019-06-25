package com.starbus.starbus.Service;

import com.starbus.starbus.Domain.User;

import java.util.HashMap;

public interface UserService {
    public HashMap<String, Object> Login(User user);
    public HashMap<String, Object> Register(User user) throws Exception;
}
