package com.crud.movies.crud.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.movies.crud.model.Entity.User;
import com.crud.movies.crud.model.Repository.DAO.UserDAO;
import com.crud.movies.crud.service.Interfaces.UserService;
import com.crud.movies.crud.service.errors.ServiceRuleException;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDao;

    @Override
    public User createUser(User user) {
        validateEmail(user.getEmail());
        return userDao.create(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.read();
    }

    @Override
    public void validateEmail(String email) throws ServiceRuleException {
        User user = searchByEmail(email);
        if (user != null) {
            throw new ServiceRuleException("There is already a registered user with this email!");
        }
    }

    @Override
    public User searchByEmail(String email) {
        User user = userDao.find("User_Email", email);
        return user;
    }

}
