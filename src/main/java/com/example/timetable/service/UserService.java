package com.example.timetable.service;

import com.example.timetable.DTO.ChangeUserDTO;
import com.example.timetable.DTO.CreateUserDTO;
import com.example.timetable.DTO.ResetPasswordUserDTO;
import com.example.timetable.entity.Role;
import com.example.timetable.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveStudent(CreateUserDTO userDTO);

    void saveTeacher(CreateUserDTO userDTO);

    void changeStudent(ChangeUserDTO userDTO);

    void changeTeacher(ChangeUserDTO userDTO);

    Optional<User> findUserByUsername(CreateUserDTO userDTO);

    Optional<User> findUserByEmail(CreateUserDTO userDTO);

    Optional<User> findUserByEmail(String email);

    List<User> findAllStudentsByRole(Role role);

    Optional<User> findUserById(int id);

    void activateUser(int id);

    void deactivateUser(int id);

    void deleteUser(int id);

    void resetPassword(ResetPasswordUserDTO userDTO);

    Optional<User> findUserByVerifyUserToken(String token);
}
