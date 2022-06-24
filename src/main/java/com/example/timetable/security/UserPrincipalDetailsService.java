package com.example.timetable.security;

import com.example.timetable.entity.User;
import com.example.timetable.exception.ConfirmEmailException;
import com.example.timetable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userPrincipalDetailsService")
@RequiredArgsConstructor
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s doesn't exists", username)));

        if (!user.getVerifyUser().getIsActive()) {
            throw new ConfirmEmailException(String.format("Пользователь не подтвердил свою почту"));
        }

        return new UserPrincipal(user);
    }


}
