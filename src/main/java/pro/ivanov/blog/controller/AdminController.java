package pro.ivanov.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.entity.User;
import pro.ivanov.blog.inputModel.ArticleModel;
import pro.ivanov.blog.repository.ArticleRepository;
import pro.ivanov.blog.repository.CategoryRepository;
import pro.ivanov.blog.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ArticleRepository articleRepository;

    public AdminController(UserRepository userRepository, CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
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
        List<Category> categories = this.categoryRepository.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("article", new ArticleModel());

        return "admin/articles/create";
    }

    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute ArticleModel articleModel) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category category = this.categoryRepository.findById(articleModel.getCategory()).orElseThrow();

        Article article = new Article();

        article.setAuthor(author);
        article.setCategory(category);
        article.setTitle(articleModel.getTitle());
        article.setContent(articleModel.getContent());

        Set<String> keywords = Arrays.stream(articleModel.getKeywords().split(",")).collect(Collectors.toSet());

        article.setKeywords(keywords);

        this.articleRepository.save(article);

        return "redirect:/";
    }

    @GetMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable long id, Model model) {
        Article article = this.articleRepository.findById(id).orElseThrow();
        List<Category> categories = this.categoryRepository.findAll();
        ArticleModel articleModel = new ArticleModel();

        articleModel.setTitle(article.getTitle());
        articleModel.setContent(article.getContent());
        articleModel.setCategory(article.getCategory().getId());
        articleModel.setKeywords(String.join(",", article.getKeywords()));

        model.addAttribute("articleId", article.getId());
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleModel);

        return "admin/articles/update";
    }

    @PostMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable long id, @ModelAttribute ArticleModel articleModel) {
        Article article = this.articleRepository.findById(id).orElseThrow();
        Category category = this.categoryRepository.findById(articleModel.getCategory()).orElseThrow();

        Set<String> keywords = Arrays.stream(articleModel.getKeywords().split(",")).collect(Collectors.toSet());

        article.setCategory(category);
        article.setContent(articleModel.getContent());
        article.setTitle(articleModel.getTitle());
        article.setModified(LocalDateTime.now());
        article.setKeywords(keywords);

        this.articleRepository.save(article);

        return "redirect:/";
    }
}
