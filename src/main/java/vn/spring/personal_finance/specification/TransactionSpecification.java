package vn.spring.personal_finance.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.spring.personal_finance.constant.TypeEnum;
import vn.spring.personal_finance.dto.request.transaction.TransactionQuery;
import vn.spring.personal_finance.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionSpecification {

    public static Specification<Transaction> hasType(TypeEnum type){
        return ((root, query, cb) -> cb.equal(root.get("type"),type));
    }
    public static Specification<Transaction> hasLessThanOrEqualAmount(BigDecimal amount){
        return ((root, query, cb) -> cb.lessThanOrEqualTo(root.get("amount"),amount));
    }
    public static Specification<Transaction> hasTransactionDateBetween(LocalDate startDate, LocalDate endDate){
        return ((root, query, cb) -> cb.between(root.get("transaction_date"),startDate, endDate));
    }
    public static Specification<Transaction> getSpecification(TransactionQuery transactionQuery){
        Specification<Transaction> spec = null;

        if(transactionQuery.getType() != null){
            spec = hasType(transactionQuery.getType());
        }
        if(transactionQuery.getAmount() != null){
            spec = spec == null ? hasLessThanOrEqualAmount(transactionQuery.getAmount()) : spec.and(hasLessThanOrEqualAmount(transactionQuery.getAmount()));
        }

        if(transactionQuery.getStartTransactionDate() != null && transactionQuery.getEndTransactionDate() != null){
            spec = spec == null ? hasTransactionDateBetween(transactionQuery.getStartTransactionDate(),transactionQuery.getEndTransactionDate()) : spec.and(hasTransactionDateBetween(transactionQuery.getStartTransactionDate(),transactionQuery.getEndTransactionDate()));
        }

        return spec;
    }
}
