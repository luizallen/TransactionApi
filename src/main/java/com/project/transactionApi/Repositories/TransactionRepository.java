package com.project.transactionApi.Repositories;

import com.project.transactionApi.Models.Transaction;
import com.project.transactionApi.Repositories.Abstractions.ITransactionRepository;

public class TransactionRepository implements ITransactionRepository {
    @Override
    public void save(Transaction transaction) {

    }

    @Override
    public Transaction get(int transactionId) {
        return null;
    }
}
