package com.moonsuns.myblog.repository;

import com.moonsuns.myblog.domain.User;

import java.util.List;

public interface UserRepository {
    User saveOrUpdateUser(User user);

    void deleteUser(Long id);

    User getUserByid(Long id);

    List<User> listUsers();
}
