package com.project.transactionApi.Controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.transactionApi.Models.Account;
import com.project.transactionApi.Models.AccountTransaction;
import com.project.transactionApi.Models.Transaction;
import com.project.transactionApi.Repositories.Abstractions.IAccountRepository;
import com.project.transactionApi.Repositories.Abstractions.IAccountTransactionsRepository;
import com.project.transactionApi.Requests.AccountRequest;
import com.project.transactionApi.Responses.CurrentAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AccountController {
    private IAccountRepository accountRepository;

    private IAccountTransactionsRepository transactionRepository;

    private ObjectMapper mapper;

    @Autowired
    public AccountController(IAccountRepository accountRepository,
            IAccountTransactionsRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.mapper = new ObjectMapper();
    }

    @GetMapping("/api/accounts/{accountId}")
    public ResponseEntity<CurrentAccountResponse> getAccount(@PathVariable("accountId") String accountId){
        if(accountId.equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var optAccount = accountRepository.findById(accountId);

        if (optAccount.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction does not exist.");

        Optional<AccountTransaction> optAccountTransaction = transactionRepository.findById(accountId);

        var transactions = GetListOfTransactions(optAccountTransaction);

        var currentAccountResponse = CreateCurrentAccountResponse(optAccount.get(), transactions);

        return new ResponseEntity<>(currentAccountResponse, HttpStatus.OK);
    }

    @PostMapping("/api/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest){
        Account account = new Account(
                UUID.randomUUID().toString(),
                accountRequest.documentNumber);

        accountRepository.save(account);

        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }

    private CurrentAccountResponse CreateCurrentAccountResponse(Account account, List<Transaction> transactions){
        return new CurrentAccountResponse(
                account.getAccountId(),
                account.getDocumentNumber(),
                GetCurrentAmount(transactions),
                transactions.size());
    }

    private List<Transaction> GetListOfTransactions(Optional<AccountTransaction> optAccountTransaction){
        if(optAccountTransaction.isEmpty())
            return new ArrayList<>();
        else
             return mapper.convertValue(optAccountTransaction.get().getTransactions(), new TypeReference<>(){});
    }

    private int GetCurrentAmount(List<Transaction> transactions){
        return  transactions.stream().mapToInt(Transaction::getAmount).sum();
    }
}
