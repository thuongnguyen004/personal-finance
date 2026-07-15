package vn.spring.personal_finance.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.spring.personal_finance.dto.request.CategoryQuery;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.dto.request.CategoryRequestDTO;
import vn.spring.personal_finance.dto.response.CategoryResponseDTO;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.mapper.category.CategoryMapper;
import vn.spring.personal_finance.repository.CategoryRepository;
import vn.spring.personal_finance.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper,CategoryRepository categoryRepository ){
        this.categoryMapper =categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO req){
        // input - data
        boolean isCategoryExists = this.categoryRepository.existsByName(req.getName());
        // logic - interaction
        Category category = this.categoryMapper.toEntity(req);
        if(isCategoryExists){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        // return output - save
        Category newCategory = this.categoryRepository.save(category);
        return this.categoryMapper.toResponse(newCategory);
    }

    public PaginationResponse<List<CategoryResponseDTO>> getCategories(CategoryQuery query){
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getSize());
        Page<Category> categoryPage = this.categoryRepository.findAll(pageable);
        Page<CategoryResponseDTO> dtoPage = categoryPage.map(this.categoryMapper::toResponse);
        return PaginationResponse.setPaginate(dtoPage);
    }

    public CategoryResponseDTO getCategoryById(long id){
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return this.categoryMapper.toResponse(category);
    }

    public CategoryResponseDTO updateCategory(CategoryRequestDTO req, long id){
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
            this.categoryMapper.updateEntity(category,req);
            this.categoryRepository.save(category);
        return this.categoryMapper.toResponse(category);
    }
    public void deleteCategory(long id){
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        this.categoryRepository.deleteById(id);
    }
}
