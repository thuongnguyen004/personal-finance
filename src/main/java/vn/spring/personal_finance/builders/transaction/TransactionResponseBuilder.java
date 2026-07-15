package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.response.transaction.TransactionResponseDTO;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionResponseBuilder {
        public TransactionResponseDTO build(Transaction transaction){
        return new TransactionResponseDTO(
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
