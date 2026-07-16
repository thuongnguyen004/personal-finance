package vn.spring.personal_finance.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.spring.personal_finance.constant.TypeEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private long id;

    private String name;

    private TypeEnum type;

    private LocalDateTime createdAt;
}
