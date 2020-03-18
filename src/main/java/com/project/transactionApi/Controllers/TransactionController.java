package com.project.transactionApi.Controllers;

import com.project.transactionApi.Models.Transaction;
import com.project.transactionApi.Repositories.Abstractions.ITransactionRepository;
import com.project.transactionApi.Requests.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    public ITransactionRepository transactionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(int id){
        Transaction transaction = transactionRepository.get(id);

        if(transaction == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction does not exists");

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest){
        Transaction transaction = new Transaction(
                transactionRequest.transactionId,
                transactionRequest.accountId,
                transactionRequest.operationType,
                transactionRequest.amount,
                transactionRequest.eventDate
        );

        transactionRepository.save(transaction);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
