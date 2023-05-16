package pro.ivanov.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.repository.ArticleRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    private final ArticleRepository articleRepository;

    public ArchiveController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/{month}/{year}")
    public String index(@PathVariable int month, @PathVariable int year, Model model) {
       List<Article> articles = this.articleRepository.findAll().stream().filter(f -> f.getCreatedOn().getMonth().getValue() == month && f.getCreatedOn().getYear() == year).toList();

       model.addAttribute("articles", articles);

       return "home/archive";
    }
}
