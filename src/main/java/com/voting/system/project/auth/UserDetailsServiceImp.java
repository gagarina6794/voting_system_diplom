package com.voting.system.project.auth;

import com.voting.system.project.model.User;
import com.voting.system.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final String userEmail = email != null ? email.toLowerCase() : null;
        final User user = userEmail != null ? userRepository.findUserByEmail(userEmail) : null;
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " is not exist");
        }
        return new UserPrincipal(user);
    }
}
