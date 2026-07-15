package vn.spring.personal_finance.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.spring.personal_finance.builders.category.CategoryBuilder;
import vn.spring.personal_finance.builders.category.CategoryResponseBuilder;
import vn.spring.personal_finance.builders.category.CategoryUpdateBuilder;
import vn.spring.personal_finance.dto.request.category.CategoryQuery;
import vn.spring.personal_finance.dto.request.category.CategoryRequestDTO;
import vn.spring.personal_finance.dto.response.category.CategoryResponseDTO;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.service.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryBuilder categoryBuilder;
    private final CategoryResponseBuilder categoryResponseBuilder;
    private final CategoryUpdateBuilder categoryUpdateBuilder;

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO request){

        Category category = categoryBuilder.build(request);
        Category createdCategory = this.categoryService.createCategory(category);
        CategoryResponseDTO responseDTO = categoryResponseBuilder.build(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/categories")
    public ResponseEntity<PaginationResponse<List<CategoryResponseDTO>>> getCategories( CategoryQuery query){

        Page<Category> categoryPage = this.categoryService.getCategories(query);
        Page<CategoryResponseDTO> categoryResponse = categoryPage.map(categoryResponseBuilder::build);
        PaginationResponse<List<CategoryResponseDTO>> listCategory = PaginationResponse.setPaginate(categoryResponse);

        return ResponseEntity.ok().body(listCategory);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById( @PathVariable("id") long id){

        Category category = this.categoryService.getCategoryById(id);
        CategoryResponseDTO response = this.categoryResponseBuilder.build(category);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory( @PathVariable("id") long id, @RequestBody CategoryRequestDTO request){
        Category category = this.categoryBuilder.build(request);
        Category updatedCategory  = this.categoryService.updateCategory(category, id);
        CategoryResponseDTO categoryResponse = this.categoryResponseBuilder.build(updatedCategory);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory( @PathVariable("id") long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(null);
    }
}
