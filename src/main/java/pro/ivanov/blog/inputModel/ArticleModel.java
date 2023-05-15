package pro.ivanov.blog.inputModel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleModel {
    private String title;

    private String content;
}
