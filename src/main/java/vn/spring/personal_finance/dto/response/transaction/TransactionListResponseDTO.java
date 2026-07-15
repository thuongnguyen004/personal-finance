package vn.spring.personal_finance.dto.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.spring.personal_finance.constant.TypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionListResponseDTO {
    private long id;

    private BigDecimal amount;

    private TypeEnum type;

    private LocalDate transaction_date;

    private long category_id;

    private String category_name;

}
