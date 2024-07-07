package com.empik.githubapi.repository;

import com.empik.githubapi.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    default User findOrCreateByLogin(String login) {
        Optional<User> user = this.findByLogin(login);
        if (user.isPresent()) {
            return user.get();
        }
        User newUser =
                User.builder()
                        .login(login)
                        .requestCount(0)
                        .build();
        save(newUser);
        return newUser;
    }
}
