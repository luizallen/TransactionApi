package com.project.transactionApi.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CurrentAccountResponse {
    public String accountId;
    public String documentNumber;
    public int currentAmount;
    public int transactionsCount;
}