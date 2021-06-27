package com.crud.movies.crud.service;

import java.util.List;
import java.util.Optional;

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
        if (searchByEmail(email).isPresent()) {
            throw new ServiceRuleException("There is already a registered user with this email!");
        }
    }

    @Override
    public Optional<User> searchByEmail(String email) {
        return userDao.find("email", email);
    }

}
