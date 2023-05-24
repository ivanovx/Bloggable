package pro.ivanov.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ivanov.blog.entity.Category;
import pro.ivanov.blog.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return this.categoryRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Category getCategory(String name) {
        return this.categoryRepository.findByName(name).orElseThrow();
    }

    public Category createCategory(Category category){
        return this.categoryRepository.save(category);
    }

    public Category createCategory(String name) {
        return this.categoryRepository.save(new Category(name));
    }
}
