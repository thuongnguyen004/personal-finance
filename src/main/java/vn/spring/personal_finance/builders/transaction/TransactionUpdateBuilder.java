package vn.spring.personal_finance.builders.transaction;

import org.springframework.stereotype.Component;
import vn.spring.personal_finance.entity.Transaction;

@Component
public class TransactionUpdateBuilder {

    public void buildUpdate(Transaction currentTransaction, Transaction updatedTransaction){
        if(updatedTransaction.getAmount() != null){
            currentTransaction.setAmount(updatedTransaction.getAmount());
        }
        if(updatedTransaction.getType() != null){
            currentTransaction.setType(updatedTransaction.getType());
        }
        if(updatedTransaction.getDescription() != null){
            currentTransaction.setDescription(updatedTransaction.getDescription());
        }
        if(updatedTransaction.getTransactionDate() != null){
            currentTransaction.setTransactionDate(updatedTransaction.getTransactionDate());
        }

    }
}
