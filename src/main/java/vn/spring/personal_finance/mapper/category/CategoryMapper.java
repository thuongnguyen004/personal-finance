package vn.spring.personal_finance.mapper.category;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.request.CategoryRequestDTO;
import vn.spring.personal_finance.dto.response.CategoryResponseDTO;
import vn.spring.personal_finance.entity.Category;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequestDTO dto) {
        return Category.builder()
                .name(dto.getName())
                .type(dto.getType())
                .build();
    }
    public CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getType(),
                category.getCreatedAt()
        );
    }

    public void updateEntity(Category category, CategoryRequestDTO dto) {
        if(dto.getName() != null){
            category.setName(dto.getName());
        }
        if(dto.getType() != null){
            category.setType(dto.getType());
        }
    }
}
