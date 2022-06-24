package com.example.timetable.service;

import com.example.timetable.entity.User;
import com.example.timetable.entity.VerifyUser;

import java.util.Optional;

public interface VerifyUserService {
    void save(User user);

    void save(VerifyUser verifyUser);

    Optional<VerifyUser> findVerifyUserById(int id);

    Optional<VerifyUser> findVerifyUserByToken(String token);

    void resetPassword(User user);

    void updateToken(User user);
}
