package pro.ivanov.blog.inputModel;

import lombok.Data;

@Data
public class ArticleModel {
    private String title;

    private String content;

    private long category;

    private String keywords;
}
