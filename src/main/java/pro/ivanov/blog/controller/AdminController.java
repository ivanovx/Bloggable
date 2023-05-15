package pro.ivanov.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.entity.User;
import pro.ivanov.blog.inputModel.ArticleModel;
import pro.ivanov.blog.repository.ArticleRepository;
import pro.ivanov.blog.repository.UserRepository;

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
    public String index() {
        return "admin/index";
    }

    @GetMapping("/article/create")
    public String createArticle(Model model) {
        model.addAttribute("article", new ArticleModel());

        return "admin/article/create";
    }

    @PostMapping("/article/create")
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
}
