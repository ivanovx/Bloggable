package pro.ivanov.blog;

import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.password.PasswordEncoder;

import pro.ivanov.blog.entity.Role;
import pro.ivanov.blog.entity.User;
import pro.ivanov.blog.repository.UserRepository;

@Component
public class ApplicationInit implements ApplicationRunner {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public ApplicationInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.userRepository.count() == 0) {
            User user = new User();

            user.setEmail("csyntax@outlook.com");
            user.setName("Ivan Ivanov");
            user.setRole(Role.ADMIN);
            user.setPassword(this.passwordEncoder.encode("csyntax"));
            user.setUsername("csyntax");

            this.userRepository.save(user);
        }
    }
}
