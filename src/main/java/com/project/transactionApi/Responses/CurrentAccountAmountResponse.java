package com.project.transactionApi.Responses;

import lombok.Data;

@Data
public class CurrentAccountAmountResponse {
    public String accountId;
    public String currentAmount;
    public int transactionsCount;
}