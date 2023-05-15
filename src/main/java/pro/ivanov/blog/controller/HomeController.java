package pro.ivanov.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.repository.ArticleRepository;

import java.util.List;


@Controller
public class HomeController {
    private final ArticleRepository articleRepository;

    public HomeController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @RequestMapping("/")
    public String index() {
        List<Article> articles = this.articleRepository.findAll();

        return "index";
    }
}
