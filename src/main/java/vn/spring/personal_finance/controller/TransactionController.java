package vn.spring.personal_finance.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.spring.personal_finance.builders.transaction.TransactionBuilder;
import vn.spring.personal_finance.builders.transaction.TransactionListResponseBuilder;
import vn.spring.personal_finance.builders.transaction.TransactionResponseBuilder;
import vn.spring.personal_finance.builders.transaction.TransactionUpdateBuilder;
import vn.spring.personal_finance.dto.request.transaction.TransactionQuery;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequestDTO;
import vn.spring.personal_finance.dto.response.PaginationResponse;
import vn.spring.personal_finance.dto.response.transaction.TransactionListResponseDTO;
import vn.spring.personal_finance.dto.response.transaction.TransactionResponseDTO;
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
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO request){
        Transaction transaction = this.transactionBuilder.build(request);
        Transaction newTransaction = this.transactionService.createTransaction(transaction);

        TransactionResponseDTO transactionResponse = this.transactionResponseBuilder.build(newTransaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

//    @GetMapping("/transactions")
//    public ResponseEntity<PaginationResponse<List<TransactionListResponseDTO>>> getTransactions(TransactionQuery query){
//        PaginationResponse<List<TransactionListResponseDTO>> listTransaction = this.transactionService.getTransactions(query);
//
//        return ResponseEntity.ok().body(listTransaction);
//    }
//
//    @GetMapping("/transactions/{id}")
//    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable("id") long id){
//        TransactionResponseDTO transaction = this.transactionService.getTransactionById(id);
//        return ResponseEntity.ok().body(transaction);
//    }
//
//    @PutMapping("/transactions/{id}")
//    public ResponseEntity<TransactionResponseDTO> updateTransaction(@RequestBody TransactionRequestDTO req, @PathVariable("id") long id){
//        TransactionResponseDTO transaction = this.transactionService.updateTransaction(req,id);
//
//        return ResponseEntity.ok().body(transaction);
//    }
//
//    @DeleteMapping("/transactions/{id}")
//    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") long id){
//        this.transactionService.deleteTransaction(id);
//        return ResponseEntity.ok().body(null);
//    }
}
