package pro.ivanov.blog.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ivanov.blog.entity.Role;
import pro.ivanov.blog.entity.User;
import pro.ivanov.blog.repository.UserRepository;

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
        return this.userRepository.findByUsername(username).orElseThrow();
    }

    public static User getActiveUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User createUser(String name, String email, String username, String password, Role role) {
        User user = new User();

        user.setRole(role);
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setActive(true);

        return this.userRepository.save(user);
    }

    public User createUser(String name, String email, String username, String password) {
        return this.createUser(name, email, username, password, Role.USER);
    }

    public User createAdmin(String name, String email, String username, String password) {
        return this.createUser(name, email, username, password, Role.ADMIN);
    }
}
