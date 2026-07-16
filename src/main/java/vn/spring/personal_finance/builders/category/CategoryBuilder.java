package vn.spring.personal_finance.builders.category;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.request.category.CategoryRequest;
import vn.spring.personal_finance.entity.Category;

@Component
public class CategoryBuilder {

    public Category build(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .type(categoryRequest.getType())
                .build();
    }
}
