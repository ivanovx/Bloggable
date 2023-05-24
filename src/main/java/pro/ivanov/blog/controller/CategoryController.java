package pro.ivanov.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.service.ArticleService;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final ArticleService articleService;

    public CategoryController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{name}")
    public String index(@PathVariable String name, Model model) {
        List<Article> articles = this.articleService.getArticlesByCategory(name);

        model.addAttribute("articles", articles);

        return "home/category";
    }
}
