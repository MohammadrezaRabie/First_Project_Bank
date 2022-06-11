package com.reza.database;


import com.reza.entity.Account;
import com.reza.entity.Customer;
import com.reza.entity.Transaction;
import com.reza.entity.enums.TransactionType;

import java.time.Instant;
import java.util.*;

public class BankDB {

    public static Map<Integer, Customer> table_customer = new LinkedHashMap<>();
    public static Map<Integer, Account> table_account = new LinkedHashMap<>();

    public static List<Transaction> table_transaction = new ArrayList<>();


    ////////    Add New Customer   //////////////////////////////////////////////

    public void addNewCustomer(Customer customer) {
        table_customer.put(customer.getCode(), customer);
    }

    ////////    Add New Account   //////////////////////////////////////////////

    public void addNewAccount(Account account) {
        table_account.put(account.getAccNumber(), account);
    }

    ////////    Get All Customer   //////////////////////////////////////////////

    public void getAllCustomer() {
        System.out.println(table_customer.values());
    }

    ////////    Get All Account   //////////////////////////////////////////////

    public void getAllAccount() {
        System.out.println(table_account.values());
    }


    ////////    Get Customer By Code   //////////////////////////////////////////////

    public void getCustomerByCode(int id) {

        if (table_customer.containsKey(id)) {
            System.out.println(table_customer.get(id));
        } else {
            System.out.println("Code Not Found");
        }
    }

    ////////    Get Account By Account's Number   //////////////////////////////////////////////

    public void getAccountByAccountNumber(int accNumber) {

        if (table_account.containsKey(accNumber)) {
            System.out.println(table_account.get(accNumber));
        } else {
            System.out.println("Account Number Not Found");
        }
    }

    ////////    Check Customer   //////////////////////////////////////////////

    public int checkCustomer(int id) {
        if (table_customer.containsKey(id)) {
            return id;
        }
        return 0;
    }


    ////////     Sort Account By Customer's Code    //////////////////////////////////////////////

    public void sortAccountByCustomerCode() {
        System.out.println(table_account.values().stream().sorted(new customerCodeCompare()).toList());

    }


    ////////     Sort Customer By Customer's Name   //////////////////////////////////////////////

    public void sortCustomerByCustomerName() {
        System.out.println(table_customer.values().stream().sorted(new firstNameCompare()).toList());
    }


    ////////    Sort Customer By Customer's Last Name   //////////////////////////////////////////////

    public void sortCustomersByCustomerLastName() {
        System.out.println(table_customer.values().stream().sorted(new lastNameCompare()).toList());

    }

    ////////     Customers Account   //////////////////////////////////////////////

    public void customersAccount(int code) {

        if (table_customer.containsKey(code)) {

            if (table_account.size() == 0){
                System.out.println("Customer Without An Account");
            }
            else {
                System.out.println(table_account.values().stream().
                        filter(account -> account.getCustomer_code() == code).toList());
            }

        } else {
            System.out.println("Customer Code Not Found");
        }
    }

    ////////    Check Account Number   //////////////////////////////////////////////

    public int checkAccNumber(int accNum) {

        if (table_account.containsKey(accNum)) {
            return accNum;
        }
        return 0;
    }
    ////////     Deposite   //////////////////////////////////////////////

    public Account depositeBalance(int accNum , long amount)  {

        Account account = null;

        if (checkAccNumber(accNum) != 0){

            account = table_account.get(accNum);
            long newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            table_transaction.add(new Transaction(accNum , new Date() , amount , TransactionType.DEPOSITE));
            System.out.println("Deposite Succsessfully");
            return account;
        }
        else {
            System.out.println("User Not Found");
        }
        return account;

    }

    /////////   Withdraw   ////////////////////////////////////////////////////

    public Account withdrawBalance (int accNum , long amount){

        Account account = null;

        if (checkAccNumber(accNum) != 0){

            account = table_account.get(accNum);

            if (account.getBalance() > amount){
                long newBalance = account.getBalance() - amount;
                account.setBalance(newBalance);
                System.out.println("Withdraw Succsessfully");
                table_transaction.add(new Transaction(accNum , new Date() , amount , TransactionType.WITHDRAW));
                return account;
            }
            else {
                System.out.println("Balance Is Not Enough");
            }
        }

        return account;
    }

    ////////   Money Transfer       //////////////////////////////////////////////////

    public void moneyTransfer (int source , int purpose , long amount){

        Account account = table_account.get(source);

        if (account.getBalance() > amount){

            withdrawBalance(source , amount);

            if (account.getBalance() > 500) {

                depositeBalance(purpose , amount);
                System.out.println("Transfer Succsessfully");

            }
            else {
                depositeBalance(source , amount);
                System.out.println("Balance Is Not Enough");
            }

        }
        else {
            System.out.println("Balance Is Not Enough");
        }
    }

    ////////   Show All Transaction's Account   ///////////////////////////////////////////////////////

    public void showTransactionAccount(int accNum){

        if (table_account.containsKey(accNum)){

            System.out.println(table_transaction.stream()
                    .filter(transaction -> transaction.getAccNumber() == accNum).toList());
        }
        else {
            System.out.println("Account Number Not Found");
        }



    }

    ////////     Class Customer Code Compare   //////////////////////////////////////////////

    class customerCodeCompare implements Comparator<Account> {

        @Override
        public int compare(Account o1, Account o2) {
            return o1.getCustomer_code() - o2.getCustomer_code();
        }
    }


    ////////     Class Customer First Name Compare   //////////////////////////////////////////////

    class firstNameCompare implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.getFirst_name().compareTo(o2.getFirst_name());
        }
    }

    ///////////   Class Customer Last Name Compare  ////////////////////////////////////////////////

    class lastNameCompare implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {

            if (o1.getLast_name().compareTo(o2.getLast_name()) == 0) {
                return o1.getFirst_name().compareTo(o2.getFirst_name());
            }
            return o1.getLast_name().compareTo(o2.getLast_name());
        }
    }
}






