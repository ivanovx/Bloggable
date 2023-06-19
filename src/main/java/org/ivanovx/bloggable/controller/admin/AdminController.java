package org.ivanovx.bloggable.controller.admin;

import org.apache.tomcat.util.bcel.Const;
import org.ivanovx.bloggable.entity.User;
import org.ivanovx.bloggable.util.Constants;
import org.ivanovx.bloggable.util.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.ivanovx.bloggable.service.ArticleService;
import org.ivanovx.bloggable.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ArticleService articleService;

    private final CategoryService categoryService;

    private final PasswordEncoder passwordEncoder;

    public AdminController(
            ArticleService articleService,
            CategoryService categoryService,
            PasswordEncoder passwordEncoder
    ) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.passwordEncoder = passwordEncoder;
    }

    //Default password change
    @GetMapping
    public String index(Model model) {
        long articlesCount = this.articleService.count();
        long categoriesCount = this.categoryService.count();

        model.addAttribute("articlesCount", articlesCount);
        model.addAttribute("categoriesCount", categoriesCount);

        return "admin/index";
    }
}
