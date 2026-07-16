package vn.spring.personal_finance.service;

import org.springframework.data.domain.Page;
import vn.spring.personal_finance.dto.request.category.CategoryQuery;
import vn.spring.personal_finance.entity.Category;

public interface CategoryService {
    Category createCategory(Category category);
    Page<Category> getCategories(CategoryQuery query);
    Category getCategoryById(long id);
    Category updateCategory(Category req, long id);
    void deleteCategory(long id);
}
