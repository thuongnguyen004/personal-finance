package vn.spring.personal_finance.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.spring.personal_finance.dto.request.transaction.TransactionQuery;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequestDTO;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.dto.response.transaction.TransactionListResponseDTO;
import vn.spring.personal_finance.dto.response.transaction.TransactionResponseDTO;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.entity.Transaction;
import vn.spring.personal_finance.builders.transaction.TransactionBuilder;
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
    private final TransactionBuilder transactionBuilder;

    public Transaction createTransaction(Transaction transaction){
        Category category = this.categoryRepository.findById(transaction.getCategory().getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        transaction.setCategory(category);

        return this.transactionRepository.save(transaction);
    }

//    public PaginationResponse<List<TransactionListResponseDTO>> getTransactions(TransactionQuery query){
//        Specification<Transaction> spec = TransactionSpecification.getSpecification(query);
//        Pageable pageable = PageRequest.of(query.getPage() -1, query.getSize());
//
//        Page<Transaction> transactionPage = this.transactionRepository.findAll(spec,pageable);
//        Page<TransactionListResponseDTO> dtoPage = transactionPage.map(this.transactionBuilder::toListResponse);
//
//        return PaginationResponse.setPaginate(dtoPage);
//    }
//
//    public TransactionResponseDTO getTransactionById(long id){
//        Transaction transaction = this.transactionRepository.findById(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND,"transaction not found"));
//        return this.transactionBuilder.toResponse(transaction);
//    }
//
//    public TransactionResponseDTO updateTransaction(TransactionRequestDTO req, long id){
//        Transaction transaction = this.transactionRepository.findById(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND,"transaction not found"));
//
//        Category category = this.categoryRepository.findById(req.getCategory_id()).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
//
//        this.transactionBuilder.updateEntity(transaction,req);
//        transaction.setCategory(category);
//
//        this.transactionRepository.save(transaction);
//
//        return this.transactionBuilder.toResponse(transaction);
//    }
//    public void deleteTransaction(long id){
//        Transaction transaction = this.transactionRepository.findById(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND,"transaction not found"));
//        this.transactionRepository.deleteById(id);
//
//    }
}
