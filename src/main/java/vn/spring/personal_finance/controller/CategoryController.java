package vn.spring.personal_finance.controller;

import jakarta.validation.Valid;
import org.hibernate.annotations.Filter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.spring.personal_finance.dto.request.CategoryQuery;
import vn.spring.personal_finance.dto.request.CategoryRequestDTO;
import vn.spring.personal_finance.dto.response.CategoryResponseDTO;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> creatCategory(@Valid @RequestBody CategoryRequestDTO req){
        CategoryResponseDTO newCategory = this.categoryService.createCategory(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
    @GetMapping("/categories")
    public ResponseEntity<PaginationResponse<List<CategoryResponseDTO>>> getCategories( CategoryQuery query){
        PaginationResponse<List<CategoryResponseDTO>> listCategory = this.categoryService.getCategories(query);

        return ResponseEntity.ok().body(listCategory);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById( @PathVariable("id") long id){
        CategoryResponseDTO category = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory( @PathVariable("id") long id, @RequestBody CategoryRequestDTO req){
        CategoryResponseDTO category = this.categoryService.updateCategory(req, id);
        return ResponseEntity.ok().body(category);
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> updateCategory( @PathVariable("id") long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(null);
    }
}
