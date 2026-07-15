package vn.spring.personal_finance.dto.request.category;

import lombok.Getter;
import lombok.Setter;
import vn.spring.personal_finance.constant.TypeEnum;

@Getter
@Setter
public class CategoryQuery {
    private int page = 1;
    private int size = 10;
    private String name;
    private TypeEnum type;
}
