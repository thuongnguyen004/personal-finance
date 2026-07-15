package vn.spring.personal_finance.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.spring.personal_finance.builders.category.CategoryUpdateBuilder;
import vn.spring.personal_finance.dto.request.category.CategoryQuery;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.exception.ResourceNotFoundException;
import vn.spring.personal_finance.repository.CategoryRepository;
import vn.spring.personal_finance.service.CategoryService;
import vn.spring.personal_finance.specification.CategorySpecification;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryUpdateBuilder categoryUpdateBuilder;
    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        if (this.categoryRepository.existsByName(category.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        return this.categoryRepository.save(category);
    }

    public Page<Category> getCategories(CategoryQuery query){
        Specification<Category> spec = CategorySpecification.getSpecification(query);
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getSize());

        return this.categoryRepository.findAll(spec, pageable);
    }

    public Category getCategoryById(long id){
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return category ;
    }

    public Category updateCategory(Category category, long id){
        Category currentCategory = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            this.categoryUpdateBuilder.build(currentCategory,category);
            this.categoryRepository.save(currentCategory);
        return currentCategory;
    }

    public void deleteCategory(long id){
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        this.categoryRepository.deleteById(id);
    }
}
