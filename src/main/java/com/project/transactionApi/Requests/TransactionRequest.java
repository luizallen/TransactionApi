package com.project.transactionApi.Requests;

import com.project.transactionApi.Enums.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    public int transactionId;
    public int accountId;
    public OperationTypeEnum operationType;
    public int amount;
    public LocalDateTime eventDate;
}