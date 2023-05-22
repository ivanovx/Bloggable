package pro.ivanov.blog.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pro.ivanov.blog.repository.ArticleRepository;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final ArticleRepository articleRepository;

    public GlobalControllerAdvice(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @ModelAttribute("calendar")
    public Map<String, Integer> populateCalendar() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");

        Map<String, Integer> group = this.articleRepository.findAll().stream()
                .collect(Collectors.groupingBy(article -> YearMonth.from(article.getCreated()).format(formatter),
                        TreeMap::new,
                        Collectors.summingInt(m -> m.getId().intValue())));

        return group;
    }
}