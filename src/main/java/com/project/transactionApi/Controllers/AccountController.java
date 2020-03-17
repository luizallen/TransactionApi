package com.project.transactionApi.Controllers;

import com.project.transactionApi.Models.Account;
import com.project.transactionApi.Repositories.Abstractions.IAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AccountController {
    public IAccountRepository accountRepository;

    public AccountController(IAccountRepository accountRepository) {
        if(accountRepository == null )
            throw new IllegalArgumentException();

        this.accountRepository =  accountRepository;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(int id){
        Account account = accountRepository.get(id);

        if(account == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account does not exists");

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        accountRepository.save(account);

        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }
}
