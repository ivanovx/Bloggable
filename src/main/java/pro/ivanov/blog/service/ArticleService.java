package pro.ivanov.blog.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import pro.ivanov.blog.UserUtils;
import pro.ivanov.blog.entity.Article;
import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.inputModel.ArticleModel;
import pro.ivanov.blog.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    private final CategoryService categoryService;

    public ArticleService(ArticleRepository articleRepository, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    public List<Article> getArticles() {
        return this.articleRepository.findByOrderByCreatedDesc();
    }

    @Transactional(readOnly = true)
    public Page<Article> getArticles(Pageable pageable) {
        return this.articleRepository.findByOrderByCreatedDesc(pageable);
    }

    @Transactional(readOnly = true)
    public List<Article> getArticlesByCategory(String categoryName) {
        return this.articleRepository.findAllByCategoryName(categoryName);
    }

    @Transactional(readOnly = true)
    public Article getArticle(long id) {
        Article article = this.articleRepository.findById(id).orElseThrow();

        return article;
    }

    public Article createArticle(ArticleModel model) {
        Category category = this.categoryService.getCategory(model.getCategory());

        Article article = new Article();

        article.setAuthor(UserUtils.getUser());
        article.setCategory(category);
        article.setTitle(model.getTitle());
        article.setContent(model.getContent());

        Set<String> keywords = Arrays.stream(model.getKeywords().split(",")).collect(Collectors.toSet());

        article.setKeywords(keywords);

        return this.articleRepository.save(article);
    }

    public Article updateArticle(long id, ArticleModel model) {
        Article article = this.articleRepository.findById(id).orElseThrow();
        Category category = this.categoryService.getCategory(model.getCategory());
        Set<String> keywords = Arrays.stream(model.getKeywords().split(",")).collect(Collectors.toSet());

        article.setCategory(category);
        article.setContent(model.getContent());
        article.setTitle(model.getTitle());
        article.setModified(LocalDateTime.now());
        article.setKeywords(keywords);

        return this.articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Article> getArchive(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);

        List<Article> articles = this.getArticles()
                .stream()
                .filter(article -> YearMonth.from(article.getCreated()).compareTo(yearMonth) == 0)
                .toList();

        return articles;
    }

    @Transactional(readOnly = true)
    public Map<YearMonth, Long> createArchive() {
        return this.getArticles()
                .stream()
                .collect(Collectors.groupingBy(article -> YearMonth.from(article.getCreated()),
                        TreeMap::new,
                        Collectors.counting()
                ));
    }


}
