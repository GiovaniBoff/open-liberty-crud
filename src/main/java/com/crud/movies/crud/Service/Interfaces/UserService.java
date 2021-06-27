package com.crud.movies.crud.service.Interfaces;

import java.util.List;
import java.util.Optional;

import com.crud.movies.crud.model.Entity.User;
import com.crud.movies.crud.service.errors.ServiceRuleException;

public interface UserService {
    User createUser(User user);

    List<User> listUsers();

    void validateEmail(String email) throws ServiceRuleException;

    Optional<User> searchByEmail(String email);
}
