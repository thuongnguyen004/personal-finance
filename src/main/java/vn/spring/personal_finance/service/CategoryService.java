package vn.spring.personal_finance.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.spring.personal_finance.dto.request.CategoryQuery;
import vn.spring.personal_finance.dto.request.CategoryRequestDTO;
import vn.spring.personal_finance.dto.response.CategoryResponseDTO;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO req);
    PaginationResponse<List<CategoryResponseDTO>> getCategories(CategoryQuery query);
    CategoryResponseDTO getCategoryById(long id);
    CategoryResponseDTO updateCategory(CategoryRequestDTO req, long id);
    void deleteCategory(long id);
}
