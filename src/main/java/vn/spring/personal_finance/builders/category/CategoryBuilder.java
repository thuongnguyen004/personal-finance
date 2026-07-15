package vn.spring.personal_finance.builders.category;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.request.category.CategoryRequestDTO;
import vn.spring.personal_finance.entity.Category;

@Component
public class CategoryBuilder {

    public Category build(CategoryRequestDTO categoryRequestDTO) {
        return Category.builder()
                .name(categoryRequestDTO.getName())
                .type(categoryRequestDTO.getType())
                .build();
    }
}
