package com.project.transactionApi.Repositories.Abstractions;

import com.project.transactionApi.Models.Transaction;

public interface ITransactionRepository {
    void save(Transaction transaction);
    Transaction get(int transactionId);
}
