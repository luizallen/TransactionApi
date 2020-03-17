package com.project.transactionApi.Repositories;

import com.project.transactionApi.Models.Account;
import com.project.transactionApi.Repositories.Abstractions.IAccountRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountRepository implements IAccountRepository {

    public void save(Account account) {}

    public Account get(int accountId) {
        return null;
    }
}
