package com.project.transactionApi.Requests;

import com.project.transactionApi.Enums.OperationTypeEnum;
import lombok.Data;

@Data
public class TransactionRequest {
    public String accountId;
    public OperationTypeEnum operationType;
    public String amount;
}