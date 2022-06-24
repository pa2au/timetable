package com.example.timetable.service;

import com.example.timetable.DTO.ChangeUserDTO;
import com.example.timetable.DTO.CreateUserDTO;
import com.example.timetable.DTO.ResetPasswordUserDTO;
import com.example.timetable.entity.Role;
import com.example.timetable.entity.User;
import com.example.timetable.repository.FacultyRepository;
import com.example.timetable.repository.GroupRepository;
import com.example.timetable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;
    private final com.example.timetable.service.VerifyUserService verifyUserService;
    private final FacultyRepository facultyRepository;

    @Override
    public void saveStudent(CreateUserDTO userDTO) {
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .patronymic(userDTO.getPatronymic())
                .mobilePhoneNumber(userDTO.getMobilePhoneNumber())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .group(groupRepository.getById(userDTO.getGroupId()))
                .role(Role.STUDENT)
                .build();
        userRepository.save(user);
        verifyUserService.save(user);
    }

    @Override
    public void saveTeacher(CreateUserDTO userDTO) {
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .patronymic(userDTO.getPatronymic())
                .mobilePhoneNumber(userDTO.getMobilePhoneNumber())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .faculty(facultyRepository.getById(userDTO.getFacultyId()))
                .role(Role.TEACHER)
                .build();
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(CreateUserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername());
    }

    @Override
    public Optional<User> findUserByEmail(CreateUserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllStudentsByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public void activateUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with id %s doesn't exists", id)));
        user.setIsEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with id %s doesn't exists", id)));
        user.setIsEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void resetPassword(ResetPasswordUserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with id %s doesn't exists", userDTO.getUserId())));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        verifyUserService.updateToken(user);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByVerifyUserToken(String token) {
        return userRepository.findByVerifyUserToken(token);
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void changeStudent(ChangeUserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).orElseThrow(() -> new UsernameNotFoundException(String.format("user with id %s doesn't exists" + userDTO.getUserId())));

        user.setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setPatronymic(userDTO.getPatronymic())
                .setMobilePhoneNumber(userDTO.getMobilePhoneNumber())
                .setUsername(userDTO.getUsername())
                .setEmail(userDTO.getEmail())
                .setFaculty(null)
                .setGroup(groupRepository.getById(userDTO.getGroupId()))
                .setRole(Role.STUDENT);
        userRepository.save(user);
    }

    @Override
    public void changeTeacher(ChangeUserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).orElseThrow(() -> new UsernameNotFoundException(String.format("user with id %s doesn't exists" + userDTO.getUserId())));

        user.setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setPatronymic(userDTO.getLastName())
                .setMobilePhoneNumber(userDTO.getMobilePhoneNumber())
                .setUsername(userDTO.getUsername())
                .setEmail(userDTO.getLastName())
                .setFaculty(facultyRepository.getById(userDTO.getFacultyId()))
                .setGroup(null)
                .setRole(Role.TEACHER);

        userRepository.save(user);
    }
}
