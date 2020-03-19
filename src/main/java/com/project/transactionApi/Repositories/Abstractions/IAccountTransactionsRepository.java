package com.project.transactionApi.Repositories.Abstractions;

import com.project.transactionApi.Models.AccountTransaction;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface IAccountTransactionsRepository extends CrudRepository<AccountTransaction, String> {
}