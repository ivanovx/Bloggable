package pro.ivanov.blog.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.repository.ArticleRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ArticleRepository articleRepository;

    public HomeController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Article> articles = this.articleRepository.findAll();

        model.addAttribute("articles", articles);

        return "home/index";
    }

    @GetMapping("{id}")
    public String article(@PathVariable long id, Model model) {
        Article article = this.articleRepository.findById(id).orElseThrow();

        model.addAttribute("article", article);

        return "home/article";
    }
}