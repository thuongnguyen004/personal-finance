package vn.spring.personal_finance.service;

import org.springframework.data.domain.Page;
import vn.spring.personal_finance.dto.request.transaction.TransactionQuery;
import vn.spring.personal_finance.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction request);
    Page<Transaction> getTransactions(TransactionQuery query);
    Transaction getTransactionById(long id);
    Transaction updateTransaction(Transaction request, long id);
    void deleteTransaction(long id);
}
