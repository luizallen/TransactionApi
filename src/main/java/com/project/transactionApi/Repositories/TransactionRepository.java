package com.project.transactionApi.Repositories;

import com.project.transactionApi.Models.Transaction;
import com.project.transactionApi.Repositories.Abstractions.ITransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepository implements ITransactionRepository {
    public void save(Transaction transaction) {    }

    public Transaction get(int transactionId) {
        return null;
    }
}
