package com.example.todolist.services;

import com.example.todolist.models.User;
import com.example.todolist.models.UserRole;
import com.example.todolist.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserService implements UserDetailsService {

    private final UserRepository m_userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        m_userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = m_userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                userFromDb.getUsername(),
                userFromDb.getPassword(),
                mapRolesToAuthorities(
                        userFromDb.getRoles()
                ));
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(Set<UserRole> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

    public void addUser(User user) throws Exception {
        User userFromDb = m_userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            throw new IllegalArgumentException("User already exists");
        }
        user.setRoles(Collections.singleton(UserRole.USER));
        user.setActive(true);
        m_userRepository.save(user);
    }
}
