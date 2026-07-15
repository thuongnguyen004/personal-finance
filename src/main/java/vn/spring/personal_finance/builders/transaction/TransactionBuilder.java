package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequestDTO;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionBuilder {
    public Transaction build(TransactionRequestDTO request){
        return Transaction.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .description(request.getDescription())
                .transactionDate(request.getTransactionDate())
                .category(
                        Category.builder()
                                .id(request.getCategoryId())
                                .build()
                )
                .build();
    }
}
