package pro.ivanov.blog.inputModel;

import lombok.Builder;
import lombok.Data;
import pro.ivanov.blog.entity.Article;

@Data
@Builder
public class ArticleModel {
    private String title;

    private String content;

    private long category;

    private String keywords;

    public ArticleModel() { }

    public static ArticleModel of(Article article) {
        return ArticleModel
                .builder()
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory().getId())
                .keywords(String.join(",", article.getKeywords()))
                .build();
    }
}
