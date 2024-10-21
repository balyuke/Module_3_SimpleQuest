package com.javarush.balyuke;

import java.util.Optional;

public class UserDao {
    public Optional<User> findById(Long id) {
        User user = new User();
        user.setName("ben");

        return Optional.of(user);
    }
}
