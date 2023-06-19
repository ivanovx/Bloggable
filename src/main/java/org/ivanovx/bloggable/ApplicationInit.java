package org.ivanovx.bloggable;

import java.util.List;
import java.util.Map;

import org.ivanovx.bloggable.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;

import org.ivanovx.bloggable.service.CategoryService;
import org.ivanovx.bloggable.service.SettingService;
import org.ivanovx.bloggable.service.UserService;

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
            this.categoryService.createCategory(Constants.DEFAULT_CATEGORY);
        }

        if (this.userService.count() == 0) {
            this.userService.createUser(Constants.DEFAULT_ADMIN);
        }
    }
}
