package com.crud.movies.crud.service;

import java.util.List;

import javax.inject.Inject;

import com.crud.movies.crud.model.Entity.User;
import com.crud.movies.crud.model.Repository.DAO.UserDAO;
import com.crud.movies.crud.service.Interfaces.UserService;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDao;

    @Override
    public User createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public List<User> searchUser() {
        // TODO Auto-generated method stub
        return null;
    }

}
