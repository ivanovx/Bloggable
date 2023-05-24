package pro.ivanov.blog;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;

import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.entity.Setting;
import pro.ivanov.blog.service.CategoryService;
import pro.ivanov.blog.service.UserService;

@Component
public class ApplicationInit implements ApplicationRunner {
    private final UserService userService;

    private final CategoryService categoryService;


    public ApplicationInit(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
      /*  if (this.settingRepository.count() == 0) {
            List<Setting> settings = List.of(
                    new Setting("title", "Sample title"),
                    new Setting("description", "Sample description")
            );

            this.settingRepository.saveAll(settings);
        }*/

        if (this.categoryService.count() == 0) {
            List.of("default", "sample", "generic").forEach(category -> this.categoryService.createCategory(category));
        }

        if (this.userService.count() == 0) {
            this.userService.createAdmin("admin", "admin@admin.bg", "admin", "admin");
        }
    }
}
