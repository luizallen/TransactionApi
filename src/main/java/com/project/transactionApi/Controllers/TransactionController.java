package com.project.transactionApi.Controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.transactionApi.Models.AccountTransaction;
import com.project.transactionApi.Models.Transaction;
import com.project.transactionApi.Repositories.Abstractions.IAccountTransactionsRepository;
import com.project.transactionApi.Requests.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class TransactionController {
    private IAccountTransactionsRepository transactionRepository;

    private ObjectMapper mapper;

    @Autowired
    public TransactionController(IAccountTransactionsRepository transactionRepository){
        this.transactionRepository = transactionRepository;
        this.mapper = new ObjectMapper();
    }

    @GetMapping("/api/accounts/{accountId}/transactions/")
    public ResponseEntity<AccountTransaction> getTransactions(
            @PathVariable("accountId")  String accountId){
        if(accountId.equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var optTransaction = transactionRepository.findById(accountId);

        if(optTransaction.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This account does not exist");

        return new ResponseEntity<>(optTransaction.get(), HttpStatus.OK);
    }

    @GetMapping("/api/accounts/{accountId}/transactions/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(
            @PathVariable("accountId")  String accountId,
            @PathVariable("transactionId")  String transactionId){
        if(transactionId.equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<AccountTransaction> optAccountTransaction = transactionRepository.findById(accountId);

        if(optAccountTransaction.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist transactions for this account");

        AccountTransaction accountTransaction = optAccountTransaction.get();

        List<Transaction> accountList = mapper.convertValue(
                accountTransaction.getTransactions(),
                new TypeReference<>(){}
        );

        var optTransaction = accountList.stream().filter(t -> t.transactionId.equals(transactionId)).findFirst();

        if(optTransaction.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction does not exist.");

        return new ResponseEntity<>(optTransaction.get(), HttpStatus.OK);
    }

    @PostMapping("/api/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest){
        Optional<AccountTransaction> optTransaction = transactionRepository.findById(transactionRequest.accountId);

        AccountTransaction accountTransaction;

        var transaction = CreateTransaction(transactionRequest);

        if (optTransaction.isEmpty())
            accountTransaction = CreateAccountTransaction(transaction);
        else{
            accountTransaction = optTransaction.get();
            accountTransaction.transactions.add(transaction);
        }

        transactionRepository.save(accountTransaction);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    private AccountTransaction CreateAccountTransaction(Transaction transaction){
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        return new AccountTransaction(transaction.accountId, transactions);
    }

    private Transaction CreateTransaction(TransactionRequest transactionRequest){
        return new Transaction(
                UUID.randomUUID().toString(),
                transactionRequest.accountId,
                transactionRequest.operationType,
                transactionRequest.amount,
                LocalDateTime.now().toString()
        );
    }
}
