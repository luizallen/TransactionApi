package com.project.transactionApi.Repositories.Abstractions;

import com.project.transactionApi.Models.Account;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface IAccountRepository extends CrudRepository<Account, String> {
}