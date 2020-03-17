package com.project.transactionApi.Models;

import com.project.transactionApi.Enums.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    public int transactionId;
    public int accountId;
    public OperationTypeEnum operationTypeEnum;
    public int amount;
    public LocalDateTime eventDate;
}
