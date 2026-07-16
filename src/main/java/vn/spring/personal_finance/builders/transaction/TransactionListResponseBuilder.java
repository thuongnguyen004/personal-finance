package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.dto.response.transaction.TransactionListResponse;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionListResponseBuilder {
        public TransactionListResponse buildList(Transaction transaction){
        return new TransactionListResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getTransactionDate(),
                transaction.getCategory().getId(),
                transaction.getCategory().getName()
        );
    }
}
