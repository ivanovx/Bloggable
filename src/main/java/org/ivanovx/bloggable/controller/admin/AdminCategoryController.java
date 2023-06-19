package org.ivanovx.bloggable.controller.admin;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.service.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Category> categories = this.categoryService.getCategories();

        model.addAttribute("categories", categories);

        return "admin/categories/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());

        return "admin/categories/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category category) {
        this.categoryService.createCategory(category);

        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long id, Model model) {
        Category category = this.categoryService.getCategory(id);

        model.addAttribute("category", category);

        return "admin/categories/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute Category category) {
        this.categoryService.updateCategory(category);

        return "redirect:/admin";
    }
}
