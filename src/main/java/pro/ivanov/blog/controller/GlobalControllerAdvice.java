package pro.ivanov.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pro.ivanov.blog.repository.ArticleRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    private ArticleRepository articleRepository;

    @ModelAttribute("calendar")
    public  Map<YearMonth, Integer> populateCalendar() {
        Map<YearMonth, Integer> group = this.articleRepository.findAll().stream()
                .collect(Collectors.groupingBy(article -> YearMonth.from(article.getCreatedOn()),
                        TreeMap::new,
                        Collectors.summingInt(m -> m.getId().intValue())));

        return group;
    }
}