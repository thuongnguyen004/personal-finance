package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.response.transaction.TransactionListResponseDTO;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionListResponseBuilder {
        public TransactionListResponseDTO buildList(Transaction transaction){
        return new TransactionListResponseDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getTransactionDate(),
                transaction.getCategory().getId(),
                transaction.getCategory().getName()
        );
    }
}
