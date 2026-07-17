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
import vn.spring.personal_finance.dto.request.category.CategoryRequest;
import vn.spring.personal_finance.dto.response.category.CategoryResponse;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.service.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        CategoryBuilder categoryBuilder = new CategoryBuilder();

        CategoryResponseBuilder categoryResponseBuilder = new CategoryResponseBuilder();
        Category category = categoryBuilder.build(categoryRequest);

        Category createdCategory = this.categoryService.createCategory(category);
        CategoryResponse responseDTO = categoryResponseBuilder.build(createdCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/categories")
    public ResponseEntity<PaginationResponse<List<CategoryResponse>>> getCategories(CategoryQuery categoryQuery){
        CategoryResponseBuilder categoryResponseBuilder = new CategoryResponseBuilder();

        Page<Category> categoryPage = this.categoryService.getCategories(categoryQuery);
        Page<CategoryResponse> categoryResponse = categoryPage.map(categoryResponseBuilder::build);
        PaginationResponse<List<CategoryResponse>> listCategory = PaginationResponse.setPaginate(categoryResponse);

        return ResponseEntity.ok().body(listCategory);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") long id){
        CategoryResponseBuilder categoryResponseBuilder = new CategoryResponseBuilder();

        Category category = this.categoryService.getCategoryById(id);
        CategoryResponse response = categoryResponseBuilder.build(category);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") long id, @RequestBody CategoryRequest categoryRequest){
        CategoryBuilder categoryBuilder = new CategoryBuilder();
        CategoryResponseBuilder categoryResponseBuilder = new CategoryResponseBuilder();

        Category category = categoryBuilder.build(categoryRequest);
        Category updatedCategory  = this.categoryService.updateCategory(category, id);
        CategoryResponse categoryResponse = categoryResponseBuilder.build(updatedCategory);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory( @PathVariable("id") long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(null);
    }
}
