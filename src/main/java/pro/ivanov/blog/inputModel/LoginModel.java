package pro.ivanov.blog.inputModel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginModel {
    private String username;

    private String password;
}
