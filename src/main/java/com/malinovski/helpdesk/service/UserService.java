package com.malinovski.helpdesk.service;


import com.malinovski.helpdesk.model.User;

public interface UserService {
    User getUserByEmail(String email);

    String getUserEmail(String userName);
}
