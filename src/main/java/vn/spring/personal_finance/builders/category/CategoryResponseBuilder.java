package vn.spring.personal_finance.builders.category;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.response.category.CategoryResponse;
import vn.spring.personal_finance.entity.Category;

@Component
public class CategoryResponseBuilder {

    public CategoryResponse build(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getType(),
                category.getCreatedAt()
        );
    }
}
