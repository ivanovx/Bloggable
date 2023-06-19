package org.ivanovx.bloggable.service;

import org.ivanovx.bloggable.entity.Role;
import org.ivanovx.bloggable.entity.User;
import org.ivanovx.bloggable.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public long count() {
        return this.userRepository.count();
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with %s not found".formatted(username)));
    }

    public User createUser(User user) {
        String password = this.passwordEncoder.encode(user.getPassword());

        user.setPassword(password);

        return this.userRepository.save(user);
    }
}