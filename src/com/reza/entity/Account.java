package com.reza.entity;

import com.reza.entity.enums.AccountType;

public class Account {

    private int accNumber;
    private long balance;



    private static int counter = 100;
    private Enum<AccountType> accountTypeEnum;
    private int customer_code;

    public Account() {

    }

    public Account(long balance , int customer_code , Enum<AccountType> accountTypeEnum) {
        this.balance = balance;
        this.accountTypeEnum = accountTypeEnum;
        this.customer_code = customer_code;
        this.accNumber = counter;
        counter += 10;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public long getBalance() {
        return balance;
    }

    public int getCustomer_code() {
        return customer_code;
    }

    @Override
    public String toString() {
        return
                "AccountNumber = " + accNumber +
                " -----> Balance = " + balance +
                " -----> Code = " + customer_code +
                " -----> AccountType = " + accountTypeEnum +"\n";
    }
}
