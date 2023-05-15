package pro.ivanov.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.entity.User;
import pro.ivanov.blog.inputModel.ArticleModel;
import pro.ivanov.blog.repository.ArticleRepository;
import pro.ivanov.blog.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;

    public AdminController(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Article> articles = this.articleRepository.findAll();

        model.addAttribute("articles", articles);

        return "admin/index";
    }

    @GetMapping("/articles/create")
    public String createArticle(Model model) {
        model.addAttribute("article", new ArticleModel());

        return "admin/articles/create";
    }

    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute("article") ArticleModel articleModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User author = this.userRepository.findByUsername(authentication.getName()).orElseThrow();

        Article article = new Article();

        article.setAuthor(author);
        article.setTitle(articleModel.getTitle());
        article.setContent(articleModel.getContent());

        this.articleRepository.save(article);

        return "redirect:/";
    }

    @GetMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable long id, Model model) {
        Article article = this.articleRepository.findById(id).orElseThrow();

        model.addAttribute("article", article);

        return "admin/articles/update";
    }

    @PostMapping("/articles/update")
    public String updateArticle(@ModelAttribute Article article) {
        article.setUpdatedOn(LocalDateTime.now());

        this.articleRepository.save(article);

        return "redirect:/";
    }
}
