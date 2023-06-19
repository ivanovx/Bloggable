package pro.ivanov.blog.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.service.ArticleService;
import pro.ivanov.blog.service.CategoryService;
import pro.ivanov.blog.inputModel.ArticleModel;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ArticleService articleService;

    private final CategoryService categoryService;

    public AdminController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Article> articles = this.articleService.getArticles();

        model.addAttribute("articles", articles);

        return "admin/index";
    }

    @GetMapping("/articles/create")
    public String createArticle(Model model) {
        List<Category> categories = this.categoryService.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("article", new ArticleModel());

        return "admin/articles/create";
    }

    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute ArticleModel articleModel) {
        this.articleService.createArticle(articleModel);

        return "redirect:/admin";
    }

    @GetMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable long id, Model model) {
        Article article = this.articleService.getArticle(id);
        List<Category> categories = this.categoryService.getCategories();

        ArticleModel articleModel = ArticleModel.of(article);

        model.addAttribute("articleId", article.getId());
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleModel);

        return "admin/articles/update";
    }

    @PostMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable long id, @ModelAttribute ArticleModel articleModel) {
        this.articleService.updateArticle(id, articleModel);

        return "redirect:/admin";
    }
}
