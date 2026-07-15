package vn.spring.personal_finance.builders.category;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.entity.Category;

@Component
public class CategoryUpdateBuilder {
    public void build(Category currentCategory, Category category) {
        if (category.getName() != null) {
            currentCategory.setName(category.getName());
        }
        if (category.getType() != null) {
            currentCategory.setType(category.getType());
        }
    }
}
