package vn.spring.personal_finance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.spring.personal_finance.constant.TypeEnum;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.exception.ResourceConflictException;
import vn.spring.personal_finance.exception.ResourceNotFoundException;
import vn.spring.personal_finance.repository.CategoryRepository;
import vn.spring.personal_finance.service.CategoryService;
import vn.spring.personal_finance.service.impl.CategoryServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryBusinessLogicTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void givenExistingCategoryWhenCreateCategoryThenThrowResourceConflictException(){
        Category category = new Category(
            "Food",
                TypeEnum.EXPENSE
        );

        when(categoryRepository.existsByName(category.getName()))
                .thenReturn(true);

        ResourceConflictException exception = assertThrows(ResourceConflictException.class,
                () -> categoryService.createCategory(category));

        assertEquals("Category already exists", exception.getMessage());

        verify(categoryRepository).existsByName(category.getName());
        verify(categoryRepository, never()).save(any(Category.class));

    }

    @Test
    public void givenNewCategoryWhenCreateCategoryThenReturnCreatedCategory(){
        Category category = new Category(
                "Food",
                TypeEnum.EXPENSE
        );
        Category createdCategory = new Category(
                1L,
                "Food",
                TypeEnum.EXPENSE,
                LocalDateTime.now()
        );
        when(categoryRepository.existsByName(category.getName()))
                .thenReturn(false);
        when(categoryRepository.save(category))
                .thenReturn(createdCategory);

        Category result = categoryService.createCategory(category);

        assertEquals(createdCategory.getId(),result.getId());
        assertEquals(createdCategory.getName(),result.getName());
        assertEquals(createdCategory.getType(),result.getType());

        verify(categoryRepository).existsByName(category.getName());
        verify(categoryRepository).save(category);

    }

    @Test
    public void givenInvalidCategoryIdWhenGetCategoryByIdThenThrowResourceNotFoundException(){
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> categoryService.getCategoryById(categoryId));

        assertEquals("Category not found", exception.getMessage());

        verify(categoryRepository).findById(categoryId);
    }
    @Test
    public void givenExistingCategoryWhenUpdateCategoryThenReturnUpdatedCategory() {
        Long categoryId = 1L;
        Category currentCategory = new Category(
                1L,
                "Food",
                TypeEnum.EXPENSE
        );

        Category updatedCategory = new Category(
                "Salary",
                TypeEnum.INCOME
        );

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(currentCategory));


        when(categoryRepository.save(currentCategory))
                .thenReturn(currentCategory);

        Category result = categoryService.updateCategory(updatedCategory, categoryId);

        assertEquals(updatedCategory.getName(), result.getName());
        assertEquals(updatedCategory.getType(), result.getType());

        verify(categoryRepository).findById(categoryId);
        verify(categoryRepository).save(currentCategory);
    }
}
