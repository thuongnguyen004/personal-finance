package vn.spring.personal_finance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import vn.spring.personal_finance.builders.transaction.TransactionBuilder;
import vn.spring.personal_finance.builders.transaction.TransactionResponseBuilder;
import vn.spring.personal_finance.constant.TypeEnum;
import vn.spring.personal_finance.dto.request.transaction.TransactionRequest;
import vn.spring.personal_finance.dto.response.transaction.TransactionResponse;
import vn.spring.personal_finance.entity.Category;
import vn.spring.personal_finance.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildingTransactionTest {

    @Test
    public void givenTransactionRequestWhenBuildingTransactionThenReturnTransaction() throws Exception {
        // given
        TransactionRequest transactionRequest = new TransactionRequest(
                BigDecimal.valueOf(10000),
                1L,
                "abc",
                LocalDate.of(2026,7,17),
                TypeEnum.EXPENSE
        );

        Category correspondingCategory = new Category();
        correspondingCategory.setId(1L);

        // when
        TransactionBuilder transactionBuilder = new TransactionBuilder();
        Transaction transaction = transactionBuilder.build(transactionRequest, correspondingCategory);

        // then
        assertEquals(transactionRequest.getAmount(), transaction.getAmount());
        assertEquals(transactionRequest.getCategoryId(), transaction.getCategory().getId());
        assertEquals(transactionRequest.getDescription(), transaction.getDescription());
        assertEquals(transactionRequest.getTransactionDate(), transaction.getTransactionDate());
        assertEquals(transactionRequest.getType(), transaction.getType());
    }

    @Test
    void givenTransactionWhenBuildTransactionResponseThenReturnTransactionResponse() {

        Category category = new Category(
                "Food",
                TypeEnum.EXPENSE
        );

        Transaction transaction = new Transaction(
                1L,
                BigDecimal.valueOf(10000),
                TypeEnum.EXPENSE,
                "Lunch",
                LocalDate.of(2026, 7, 17),
                category,
                LocalDateTime.of(2026, 7, 17, 10, 30),
                LocalDateTime.of(2026, 7, 17, 11, 0)
        );

        TransactionResponseBuilder builder = new TransactionResponseBuilder();

        // When
        TransactionResponse response = builder.build(transaction);

        // Then
        assertEquals(transaction.getId(), response.getId());
        assertEquals(transaction.getAmount(), response.getAmount());
        assertEquals(transaction.getType(), response.getType());
        assertEquals(transaction.getDescription(), response.getDescription());
        assertEquals(transaction.getTransactionDate(), response.getTransactionDate());
        assertEquals(transaction.getCategory().getId(), response.getCategoryId());
        assertEquals(transaction.getCategory().getName(), response.getCategoryName());
        assertEquals(transaction.getCreatedAt(), response.getCreatedAt());
        assertEquals(transaction.getUpdatedAt(), response.getUpdatedAt());
    }

}
