package vn.spring.personal_finance.builders.category;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.response.category.CategoryResponseDTO;
import vn.spring.personal_finance.entity.Category;

@Component
public class CategoryResponseBuilder {

    public CategoryResponseDTO build(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getType(),
                category.getCreatedAt()
        );

        return categoryResponseDTO;
    }
}
