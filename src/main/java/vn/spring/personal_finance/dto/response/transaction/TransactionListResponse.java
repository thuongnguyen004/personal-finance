package vn.spring.personal_finance.dto.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.spring.personal_finance.constant.TypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionListResponse {
    private long id;

    private BigDecimal amount;

    private TypeEnum type;

    private LocalDate transactionDate;

    private long categoryId;

    private String categoryName;

}
