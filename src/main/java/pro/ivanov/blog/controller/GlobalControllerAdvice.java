package pro.ivanov.blog.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.service.ArticleService;
import pro.ivanov.blog.service.CategoryService;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final ArticleService articleService;

    private final CategoryService categoryService;

    public GlobalControllerAdvice(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return this.categoryService.getCategories();
    }

    @ModelAttribute("archive")
    public Map<YearMonth, Long> archive() {
        return articleService.createArchive();
    }
}