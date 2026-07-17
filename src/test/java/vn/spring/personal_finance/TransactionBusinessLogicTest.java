package vn.spring.personal_finance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.spring.personal_finance.builders.transaction.TransactionBuilder;
import vn.spring.personal_finance.constant.TypeEnum;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.entity.Transaction;
import vn.spring.personal_finance.exception.ResourceNotFoundException;
import vn.spring.personal_finance.repository.CategoryRepository;
import vn.spring.personal_finance.repository.TransactionRepository;
import vn.spring.personal_finance.service.TransactionService;
import vn.spring.personal_finance.service.impl.TransactionServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionBusinessLogicTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionBuilder transactionBuilder;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void givenExistingCategoryWhenCreateTransactionThenReturnCreatedTransaction(){
        Category category = new Category(
                1L,
                "Food",
                TypeEnum.EXPENSE
        );
        Transaction transaction = new Transaction(
                BigDecimal.valueOf(10000),
                TypeEnum.EXPENSE,
                "abc",
                LocalDate.of(2026,7,17),
                category
        );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        when(transactionRepository.save(transaction))
                .thenReturn(transaction);

        Transaction result = transactionService.createTransaction(transaction);

        assertEquals(transaction.getAmount(), result.getAmount());
        assertEquals(transaction.getType(), result.getType());
        assertEquals(transaction.getDescription(), result.getDescription());
        assertEquals(transaction.getTransactionDate(), result.getTransactionDate());
        assertEquals(transaction.getCategory().getId(), result.getCategory().getId());

        verify(categoryRepository).findById(1L);
        verify(transactionRepository).save(transaction);
    }

    @Test
    public void givenInvalidCategoryIdWhenCreateTransactionThenThrowResourceNotFoundException() {
        Category category = new Category(
                1L,
                "Food",
                TypeEnum.EXPENSE
        );

        Transaction transaction = new Transaction(
                BigDecimal.valueOf(10000),
                TypeEnum.EXPENSE,
                "abc",
                LocalDate.of(2026, 7, 17),
                category
        );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> transactionService.createTransaction(transaction)
        );

        assertEquals("Category not found", exception.getMessage());

        verify(categoryRepository).findById(1L);
        verify(transactionRepository, never()).save(any(Transaction.class));
    }
    @Test
    public void givenInvalidTransactionIdWhenGetTransactionByIdThenThrowResourceNotFoundException() {
        Long transactionId = 1L;

        when(transactionRepository.findById(transactionId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> transactionService.getTransactionById(transactionId)
        );

        assertEquals("Transaction not found", exception.getMessage());

        verify(transactionRepository).findById(transactionId);
        verifyNoMoreInteractions(transactionRepository);
    }

    @Test
    public void givenExistingTransactionIdWhenGetTransactionByIdThenReturnTransaction() {
        Category category = new Category(
                1L,
                "Food",
                TypeEnum.EXPENSE
        );

        Transaction transaction = new Transaction(
                BigDecimal.valueOf(10000),
                TypeEnum.EXPENSE,
                "abc",
                LocalDate.of(2026, 7, 17),
                category
        );
        transaction.setId(1L);

        when(transactionRepository.findById(1L))
                .thenReturn(Optional.of(transaction));

        Transaction result = transactionService.getTransactionById(1L);

        assertEquals(transaction.getId(), result.getId());
        assertEquals(transaction.getAmount(), result.getAmount());
        assertEquals(transaction.getDescription(), result.getDescription());
        assertEquals(transaction.getType(), result.getType());
        assertEquals(transaction.getTransactionDate(), result.getTransactionDate());
        assertEquals(transaction.getCategory().getId(), result.getCategory().getId());

        verify(transactionRepository).findById(1L);
    }
    @Test
    public void givenExistingTransactionAndCategoryWhenUpdateTransactionThenReturnUpdatedTransaction() {
        Category oldCategory = new Category(
                1L,
                "Food",
                TypeEnum.EXPENSE
        );

        Category newCategory = new Category(
                2L,
                "Salary",
                TypeEnum.INCOME
        );

        Transaction currentTransaction = new Transaction(
                BigDecimal.valueOf(10000),
                TypeEnum.EXPENSE,
                "Old",
                LocalDate.of(2026, 7, 17),
                oldCategory
        );
        currentTransaction.setId(1L);

        Transaction updatedTransaction = new Transaction(
                BigDecimal.valueOf(20000),
                TypeEnum.INCOME,
                "New",
                LocalDate.of(2026, 7, 18),
                newCategory
        );

        when(transactionRepository.findById(1L))
                .thenReturn(Optional.of(currentTransaction));

        when(categoryRepository.findById(2L))
                .thenReturn(Optional.of(newCategory));

        when(transactionRepository.save(currentTransaction))
                .thenReturn(currentTransaction);

        Transaction result = transactionService.updateTransaction(updatedTransaction, 1L);

        assertEquals(updatedTransaction.getAmount(), result.getAmount());
        assertEquals(updatedTransaction.getType(), result.getType());
        assertEquals(updatedTransaction.getDescription(), result.getDescription());
        assertEquals(updatedTransaction.getTransactionDate(), result.getTransactionDate());
        assertEquals(updatedTransaction.getCategory().getId(), result.getCategory().getId());

        verify(transactionRepository).findById(1L);
        verify(categoryRepository).findById(2L);
        verify(transactionRepository).save(currentTransaction);
    }
}
