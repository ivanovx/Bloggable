package pro.ivanov.blog;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;

import pro.ivanov.blog.service.CategoryService;
import pro.ivanov.blog.service.SettingService;
import pro.ivanov.blog.service.UserService;

@Component
public class ApplicationInit implements ApplicationRunner {
    private final UserService userService;

    private final SettingService settingService;

    private final CategoryService categoryService;

    public ApplicationInit(UserService userService, SettingService settingService, CategoryService categoryService) {
        this.userService = userService;
        this.settingService = settingService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.settingService.count() == 0) {
            Map.of(
                    "title", "Sample title",
                    "description", "Sample description"
            ).forEach((name, value) -> this.settingService.createSetting(name, value));
        }
        if (this.categoryService.count() == 0) {
            List.of("default", "sample", "generic").forEach(category -> this.categoryService.createCategory(category));
        }

        if (this.userService.count() == 0) {
            this.userService.createAdmin("admin", "admin@admin.bg", "admin", "admin");
        }
    }
}
