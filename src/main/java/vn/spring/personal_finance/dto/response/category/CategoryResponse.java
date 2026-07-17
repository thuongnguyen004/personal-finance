package vn.spring.personal_finance.dto.response.category;

import lombok.*;
import vn.spring.personal_finance.constant.TypeEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private long id;

    private String name;

    private TypeEnum type;

    private LocalDateTime createdAt;
}
