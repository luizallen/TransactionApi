package com.project.transactionApi.Models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.project.transactionApi.Enums.OperationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    public String transactionId;
    @DynamoDBIgnore
    public String accountId;
    public OperationTypeEnum operationType;
    public String amount;
    public String eventDate;

    public Transaction(){

    }
}
