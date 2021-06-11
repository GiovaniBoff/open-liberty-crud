package com.crud.movies.crud.service.Interfaces;

import java.util.List;

import com.crud.movies.crud.model.Entity.User;

public interface UserService {
    User createUser(User user);

    List<User> searchUser();
}
