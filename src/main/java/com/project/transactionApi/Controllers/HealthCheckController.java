package com.project.transactionApi.Controllers;

import com.project.transactionApi.Models.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @GetMapping("/{id}")
    public ResponseEntity<Account> CheckHealth(){
        return new ResponseEntity("Online", HttpStatus.OK);
    }

}