package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.response.transaction.TransactionResponse;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionResponseBuilder {
        public TransactionResponse build(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDescription(),
                transaction.getTransactionDate(),
                transaction.getCategory().getId(),
                transaction.getCategory().getName(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }
}
