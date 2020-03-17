package com.project.transactionApi.Controllers;

import com.project.transactionApi.Models.Transaction;
import com.project.transactionApi.Repositories.Abstractions.ITransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TransactionController {
    public ITransactionRepository transactionRepository;

    public TransactionController(ITransactionRepository transactionRepository) {
        if(transactionRepository == null )
            throw new IllegalArgumentException();

       this.transactionRepository =  transactionRepository;
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getAccount(int id){
        Transaction transaction = transactionRepository.get(id);

        if(transaction == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction does not exists");

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createAccount(@RequestBody Transaction transaction){
        transactionRepository.save(transaction);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
