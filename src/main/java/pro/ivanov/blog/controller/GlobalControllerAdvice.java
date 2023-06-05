package pro.ivanov.blog.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.entity.Setting;
import pro.ivanov.blog.service.ArticleService;
import pro.ivanov.blog.service.CategoryService;
import pro.ivanov.blog.service.SettingService;

import java.util.Map;
import java.util.List;
import java.time.YearMonth;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final ArticleService articleService;

    private final SettingService settingService;

    private final CategoryService categoryService;

    public GlobalControllerAdvice(
            ArticleService articleService,
            SettingService settingService,
            CategoryService categoryService
    ) {
        this.articleService = articleService;
        this.settingService = settingService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return this.categoryService.getCategories();
    }

    @ModelAttribute("settings")
    public Map<String, String> settings() {
        return Map.of();
    };

    @ModelAttribute("archive")
    public Map<YearMonth, Long> archive() {
        return articleService.createArchive();
    }
}