package com.example.timetable.repository;

import com.example.timetable.entity.Role;
import com.example.timetable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    Optional<User> findByVerifyUserToken(String token);
}
