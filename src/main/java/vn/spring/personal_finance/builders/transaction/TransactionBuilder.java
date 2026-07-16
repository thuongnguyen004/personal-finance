package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequest;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionBuilder {
    public Transaction build(TransactionRequest transactionRequest){
        return Transaction.builder()
                .amount(transactionRequest.getAmount())
                .type(transactionRequest.getType())
                .description(transactionRequest.getDescription())
                .transactionDate(transactionRequest.getTransactionDate())
                .category(
                        Category.builder()
                                .id(transactionRequest.getCategoryId())
                                .build()
                )
                .build();
    }
}
