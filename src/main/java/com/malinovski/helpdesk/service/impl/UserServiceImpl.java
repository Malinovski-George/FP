package com.malinovski.helpdesk.service.impl;


import com.malinovski.helpdesk.dao.UserDao;
import com.malinovski.helpdesk.model.User;
import com.malinovski.helpdesk.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public String getUserEmail(String userName) {
        return userDao.getUserEmail(userName);
    }
}
