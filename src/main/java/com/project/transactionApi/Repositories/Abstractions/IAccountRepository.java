package com.project.transactionApi.Repositories.Abstractions;

import com.project.transactionApi.Models.Account;

public interface IAccountRepository {
    void save(Account account);
    Account get(int accountId);
}
