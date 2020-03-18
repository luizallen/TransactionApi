package com.project.transactionApi.Controllers;

import com.project.transactionApi.Models.Account;
import com.project.transactionApi.Repositories.Abstractions.IAccountRepository;
import com.project.transactionApi.Requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    public IAccountRepository accountRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(int id){
        Account account = accountRepository.get(id);

        if(account == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account does not exists");

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest){
        Account account = new Account(accountRequest.accountId, accountRequest.documentNumber);

        accountRepository.save(account);

        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }
}
