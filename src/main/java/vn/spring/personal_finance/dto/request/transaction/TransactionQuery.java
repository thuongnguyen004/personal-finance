package vn.spring.personal_finance.dto.request.transaction;

import lombok.Getter;
import lombok.Setter;
import vn.spring.personal_finance.constant.TypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionQuery {
    private int page = 1;
    private int size = 10;
    private BigDecimal amount;
    private TypeEnum type;
    private LocalDate startTransactionDate;
    private LocalDate endTransactionDate;

}
