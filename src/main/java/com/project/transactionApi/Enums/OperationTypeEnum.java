package com.project.transactionApi.Enums;

public enum  OperationTypeEnum {
    A_VISTA(1),
    PARCELADA(2),
    SAQUE(3),
    PAGAMENTO(4);

    private final int value;

    private OperationTypeEnum(int value){
        this.value = value;
    }
}
