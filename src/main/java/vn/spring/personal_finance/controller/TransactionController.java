package vn.spring.personal_finance.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.spring.personal_finance.builders.transaction.TransactionBuilder;
import vn.spring.personal_finance.builders.transaction.TransactionListResponseBuilder;
import vn.spring.personal_finance.builders.transaction.TransactionResponseBuilder;
import vn.spring.personal_finance.dto.request.transaction.TransactionQuery;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequest;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.dto.response.transaction.TransactionListResponse;
import vn.spring.personal_finance.dto.response.transaction.TransactionResponse;
import vn.spring.personal_finance.entity.Transaction;
import vn.spring.personal_finance.service.TransactionService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionBuilder transactionBuilder;
    private final TransactionResponseBuilder transactionResponseBuilder;
    private final TransactionListResponseBuilder transactionListResponseBuilder;

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest transactionRequest){
        Transaction transaction = this.transactionBuilder.build(transactionRequest);
        Transaction newTransaction = this.transactionService.createTransaction(transaction);

        TransactionResponse transactionResponse = this.transactionResponseBuilder.build(newTransaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

    @GetMapping("/transactions")
    public ResponseEntity<PaginationResponse<List<TransactionListResponse>>> getTransactions(TransactionQuery transactionQuery){
        Page<Transaction> transactionPage = this.transactionService.getTransactions(transactionQuery);
        Page<TransactionListResponse> transactionResponse = transactionPage.map(this.transactionListResponseBuilder::buildList);
        PaginationResponse<List<TransactionListResponse>> listTransaction = PaginationResponse.setPaginate(transactionResponse);

        return ResponseEntity.ok().body(listTransaction);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable("id") long id){
        Transaction transaction =  this.transactionService.getTransactionById(id);
        TransactionResponse transactionResponse = transactionResponseBuilder.build(transaction);
        return ResponseEntity.ok().body(transactionResponse);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable("id") long id){
        Transaction transaction = this.transactionBuilder.build(transactionRequest);
        Transaction updatedTransaction = this.transactionService.updateTransaction(transaction,id);
        TransactionResponse transactionResponse = this.transactionResponseBuilder.build(updatedTransaction);

        return ResponseEntity.ok().body(transactionResponse);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") long id){
        this.transactionService.deleteTransaction(id);
        return ResponseEntity.ok().body(null);
    }
}
