package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequest;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionBuilder {
    public Transaction build(TransactionRequest transactionRequest, Category correspondingCategory){
        return new Transaction(
                transactionRequest.getAmount(),
                transactionRequest.getType(),
                transactionRequest.getDescription(),
                transactionRequest.getTransactionDate(),
                correspondingCategory
        );
    }
}
