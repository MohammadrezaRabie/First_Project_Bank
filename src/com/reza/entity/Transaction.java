package com.reza.entity;

import com.reza.entity.enums.TransactionType;

import java.util.Date;

public class Transaction {

        private int accNumber;
        private Date time;
        private long amount;
        private Enum<TransactionType> transactionTypeEnum;


    public Transaction(int accNumber, Date time, long amount, Enum<TransactionType> transactionTypeEnum) {
        this.accNumber = accNumber;
        this.time = time;
        this.amount = amount;
        this.transactionTypeEnum = transactionTypeEnum;
    }

    public int getAccNumber() {
        return accNumber;
    }

    @Override
    public String toString() {
        return
                "Account Number = " + accNumber +
                " ----> Date & Time = " + time +
                " ----> Amount = " + amount +
                " ----> TransactionType = " + transactionTypeEnum + "\n"
                ;
    }
}
