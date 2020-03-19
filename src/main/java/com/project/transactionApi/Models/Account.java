package com.project.transactionApi.Models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@DynamoDBTable(tableName = "Accounts")
public class Account {
    @DynamoDBHashKey(attributeName = "AccountId")
    private String accountId;

    @DynamoDBAttribute(attributeName = "DocumentNumber")
    private String documentNumber;

    public Account()
    {

    }
}
