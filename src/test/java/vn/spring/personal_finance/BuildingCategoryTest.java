package vn.spring.personal_finance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import vn.spring.personal_finance.builders.category.CategoryBuilder;
import vn.spring.personal_finance.builders.category.CategoryResponseBuilder;
import vn.spring.personal_finance.constant.TypeEnum;
import vn.spring.personal_finance.dto.request.category.CategoryRequest;
import vn.spring.personal_finance.dto.response.category.CategoryResponse;
import vn.spring.personal_finance.entity.Category;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildingCategoryTest {

    @Test
    public void givenCategoryRequestWhenBuildCategoryThenReturnCategory(){
        CategoryRequest categoryRequest = new CategoryRequest(
                "Food",
                TypeEnum.EXPENSE
        );
        CategoryBuilder builder = new CategoryBuilder();

        Category category = builder.build(categoryRequest);

        assertEquals(categoryRequest.getName(), category.getName());
        assertEquals(categoryRequest.getType(), category.getType());
    }
    @Test
    public void givenCategoryWhenBuildCategoryResponseThenReturnCategoryResponse(){
        Category category = new Category(
                1L,
                "Food",
                TypeEnum.EXPENSE,
                LocalDateTime.of(2027, 7, 17, 10, 30)
        );

        CategoryResponseBuilder builder = new CategoryResponseBuilder();

        CategoryResponse categoryResponse = builder.build(category);

        assertEquals(category.getId(), categoryResponse.getId());
        assertEquals(category.getName(), categoryResponse.getName());
        assertEquals(category.getType(), categoryResponse.getType());
        assertEquals(category.getCreatedAt(), categoryResponse.getCreatedAt());
    }

}
