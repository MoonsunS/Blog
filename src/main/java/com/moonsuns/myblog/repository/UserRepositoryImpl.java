package com.moonsuns.myblog.repository;

import com.moonsuns.myblog.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static AtomicLong counterId = new AtomicLong();

    private final ConcurrentMap<Long, User> userConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if (id == null) {
            id = counterId.incrementAndGet();
            user.setId(id);
        }

        this.userConcurrentMap.put(id, user);

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        this.userConcurrentMap.remove(id);
    }

    @Override
    public User getUserByid(Long id) {
        return this.userConcurrentMap.get(id);
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(this.userConcurrentMap.values());
    }
}
