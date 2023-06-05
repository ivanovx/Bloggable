package pro.ivanov.blog.inputModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ivanov.blog.entity.Article;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleModel {
    private String title;

    private String content;

    private long category;

    private String keywords;

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
