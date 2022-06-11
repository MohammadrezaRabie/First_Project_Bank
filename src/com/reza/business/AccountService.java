package com.reza.business;

import java.util.Comparator;

public interface AccountService {

    void addAccount();
    void showAllAccount();
    void findByAccountNumber();
    void deposit(int accNum , long amount);
    void withdraw(int accNum , long amount);
    void sortAccountsByCustomerCode();
    void moneyTransfer (int source , int purpose , long amount);

}
