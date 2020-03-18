package com.project.transactionApi.Models;

import com.project.transactionApi.Enums.OperationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class Transaction {
    public int transactionId;
    public int accountId;
    public OperationTypeEnum operationType;
    public int amount;
    public LocalDateTime eventDate;
}
