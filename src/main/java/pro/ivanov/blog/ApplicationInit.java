package pro.ivanov.blog;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.password.PasswordEncoder;

import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.entity.Role;
import pro.ivanov.blog.entity.Setting;
import pro.ivanov.blog.entity.User;
import pro.ivanov.blog.repository.CategoryRepository;
import pro.ivanov.blog.repository.SettingRepository;
import pro.ivanov.blog.repository.UserRepository;

@Component
public class ApplicationInit implements ApplicationRunner {
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final SettingRepository settingRepository;

    private final PasswordEncoder passwordEncoder;

    public ApplicationInit(UserRepository userRepository, CategoryRepository categoryRepository, SettingRepository settingRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.settingRepository = settingRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.settingRepository.count() == 0) {
            List<Setting> settings = List.of(
                    new Setting("title", "Sample title"),
                    new Setting("description", "Sample description")
            );

            this.settingRepository.saveAll(settings);
        }

        if (this.categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                new Category("Sample"), new Category("Other"), new Category("Generic")
            );

            this.categoryRepository.saveAll(categories);
        }

        if (this.userRepository.count() == 0) {
            User user = new User();

            user.setName("Ivan Ivanov");
            user.setUsername("csyntax");
            user.setEmail("csyntax@outlook.com");
            user.setActive(true);
            user.setRole(Role.ADMIN);
            user.setPassword(this.passwordEncoder.encode("csyntax"));

            this.userRepository.save(user);
        }
    }
}
