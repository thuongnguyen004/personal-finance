package vn.spring.personal_finance.dto.request;

import lombok.Getter;
import lombok.Setter;
import vn.spring.personal_finance.constant.CategoryTypeEnum;

@Getter
@Setter
public class CategoryQuery {
    private int page = 1;
    private int size = 10;

}
