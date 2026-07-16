package vn.spring.personal_finance.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.spring.personal_finance.builders.transaction.TransactionResponseBuilder;
import vn.spring.personal_finance.builders.transaction.TransactionUpdateBuilder;
import vn.spring.personal_finance.dto.request.transaction.TransactionQuery;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequestDTO;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.dto.response.transaction.TransactionListResponseDTO;
import vn.spring.personal_finance.dto.response.transaction.TransactionResponseDTO;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.entity.Transaction;
import vn.spring.personal_finance.builders.transaction.TransactionBuilder;
import vn.spring.personal_finance.exception.ResourceNotFoundException;
import vn.spring.personal_finance.repository.CategoryRepository;
import vn.spring.personal_finance.repository.TransactionRepository;
import vn.spring.personal_finance.service.TransactionService;
import vn.spring.personal_finance.specification.TransactionSpecification;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionUpdateBuilder transactionUpdateBuilder;

    public Transaction createTransaction(Transaction transaction){
        Category category = this.categoryRepository.findById(transaction.getCategory().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Category not found"));

        transaction.setCategory(category);

        return this.transactionRepository.save(transaction);
    }

    public Page<Transaction> getTransactions(TransactionQuery query){
        Specification<Transaction> spec = TransactionSpecification.getSpecification(query);
        Pageable pageable = PageRequest.of(query.getPage() -1, query.getSize());

        return this.transactionRepository.findAll(spec,pageable);
    }

    public Transaction getTransactionById(long id){
        Transaction transaction = this.transactionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction not found"));
        return transaction;
    }

    public Transaction updateTransaction(Transaction transaction, long id){
        Transaction currentTransaction = this.transactionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction not found"));

        Category category = this.categoryRepository.findById(transaction.getCategory().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Category not found"));

        this.transactionUpdateBuilder.buildUpdate(currentTransaction, transaction);
        currentTransaction.setCategory(category);

        this.transactionRepository.save(currentTransaction);

        return currentTransaction;
    }
    public void deleteTransaction(long id){
        Transaction transaction = this.transactionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction not found"));
        this.transactionRepository.deleteById(id);

    }
}
