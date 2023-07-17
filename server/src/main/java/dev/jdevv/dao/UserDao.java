package dev.jdevv.dao;

import dev.jdevv.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    User create(User newUser);
}
