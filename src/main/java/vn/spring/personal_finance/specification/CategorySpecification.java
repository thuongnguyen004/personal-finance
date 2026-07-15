package vn.spring.personal_finance.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.spring.personal_finance.constant.TypeEnum;
import vn.spring.personal_finance.dto.request.category.CategoryQuery;
import vn.spring.personal_finance.entity.Category;

public class CategorySpecification {

    public static Specification<Category> hasName(String name){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("name")),"%" + name.toLowerCase() + "%"));
    }

    public static Specification<Category> hasType(TypeEnum type){
        return ((root, query, cb) -> cb.equal(root.get("type"),type));
    }
    public static Specification<Category> getSpecification(CategoryQuery categoryQuery){
        Specification<Category> spec = null;

        if(categoryQuery.getName() != null){
            spec = hasName(categoryQuery.getName());
        }
        if(categoryQuery.getType() != null){
            spec = spec == null ? hasType(categoryQuery.getType()) : spec.and(hasType(categoryQuery.getType()));
        }
        return spec;
    }

}
