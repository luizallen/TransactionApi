package com.project.transactionApi.Controllers;

import com.project.transactionApi.Models.Account;
import com.project.transactionApi.Repositories.Abstractions.IAccountRepository;
import com.project.transactionApi.Requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
public class AccountController {
    @Autowired
    private IAccountRepository accountRepository;


    @GetMapping("/api/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") String id){
        if(id.equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Account> optAccount = accountRepository.findById(id);

        if(optAccount.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account does not exists");

        return new ResponseEntity<>(optAccount.get(), HttpStatus.OK);
    }

    @PostMapping("/api/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest){
        Account account = new Account(
                UUID.randomUUID().toString(),
                accountRequest.documentNumber);

        accountRepository.save(account);

        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }
}
