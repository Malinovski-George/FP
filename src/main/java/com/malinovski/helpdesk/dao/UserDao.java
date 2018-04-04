package com.malinovski.helpdesk.dao;

import com.malinovski.helpdesk.model.User;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String email);

    User getUserByName(String userName);

    String getUserEmail(String userName);

    List<User> getAllEngineers();

    List<User> getAllManagers();

    User getUserById(int owner);
}
