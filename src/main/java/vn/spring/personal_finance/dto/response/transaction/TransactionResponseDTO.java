package vn.spring.personal_finance.dto.response.transaction;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.spring.personal_finance.constant.TypeEnum;
import vn.spring.personal_finance.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private long id;

    private BigDecimal amount;

    private TypeEnum type;

    private String description;

    private LocalDate transaction_date;

    private long category_id;

    private String category_name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
