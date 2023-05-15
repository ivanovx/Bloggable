package pro.ivanov.blog.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pro.ivanov.blog.entity.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
    List<Article> findAll();
}
