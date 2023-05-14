package pro.ivanov.blog.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pro.ivanov.blog.entity.Article;

@Repository
public interface ArticleRespository extends PagingAndSortingRepository<Article, Long> {
}
