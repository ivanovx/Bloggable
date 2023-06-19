package org.ivanovx.bloggable.controller;

import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.service.ArticleService;
import org.ivanovx.bloggable.service.CategoryService;
import org.ivanovx.bloggable.service.SettingService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

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