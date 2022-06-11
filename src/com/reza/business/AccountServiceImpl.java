package com.reza.business;

import com.reza.console.Console;
import com.reza.database.BankDB;
import com.reza.entity.Account;
import com.reza.entity.Transaction;
import com.reza.entity.enums.TransactionType;

import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class AccountServiceImpl implements AccountService {

    Console console = new Console();
    BankDB bank = new BankDB();
    Scanner scanner = new Scanner(System.in);




    @Override
    public void addAccount() {

        Account account = console.createNewAccount();
        bank.addNewAccount(account);

    }

    @Override
    public void showAllAccount() {
        bank.getAllAccount();
    }

    @Override
    public void findByAccountNumber() {

        System.out.println("Please Insert Account Number For Search :");
        int accNumber = scanner.nextInt();
        bank.getAccountByAccountNumber(accNumber);
    }

    @Override
    public void sortAccountsByCustomerCode() {
        bank.sortAccountByCustomerCode();
    }

    @Override
    public void moneyTransfer(int source, int purpose, long amount) {
        bank.moneyTransfer(source , purpose , amount);
    }


    @Override
    public void deposit(int accNum , long amount) {

        bank.depositeBalance(accNum , amount);

    }

    @Override
    public void withdraw(int accNum , long amount) {
        bank.withdrawBalance(accNum , amount);
    }



}
