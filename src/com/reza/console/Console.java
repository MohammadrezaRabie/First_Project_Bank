package com.reza.console;

import com.reza.business.AccountServiceImpl;
import com.reza.business.CustomerServiceImpl;
import com.reza.database.BankDB;
import com.reza.entity.Account;
import com.reza.entity.Customer;
import com.reza.entity.enums.AccountType;
import com.reza.validation.GeneralValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console  {

    Scanner scanner = new Scanner(System.in);
    BankDB bank = new BankDB();
    GeneralValidation validation = new GeneralValidation();


/////////// First List  //////////////////////////////////////////

    public void listOfService() {

        System.out.println("Please choose an option:");
        System.out.println("-------------------------");
        System.out.println("1. Creating a new customer");
        System.out.println("2. Creating a new account");
        System.out.println("3. All Customer and Account list");
        System.out.println("4. Deposit to account");
        System.out.println("5. Withdraw from account");
        System.out.println("6. Reporting a customer's accounts");
        System.out.println("7. Reporting the transactions of an account");
        System.out.println("8. Search");
        System.out.println("9. Money Transfer");
        System.out.println("10. Exit");
        System.out.print("---> ");


    }

    ///// Menu /////////////////////////////////////////////////////////////////////////

    public void menu (){

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        AccountServiceImpl accountService = new AccountServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true){

            listOfService();
            try {
                int option = scanner.nextInt();

                if (option <= 10){

                    switch (option){

                        case 1:

                            customerService.addCustomer();
                            System.out.println("Add New Customer Succsessfully");
                            break;

                        case 2:

                            accountService.addAccount();
                            System.out.println("Add New Account Succsessfully");
                            break;

                        case 3:

                            System.out.println("1. Show All Customer");
                            System.out.println("2. Show All Account");
                            System.out.print("----> ");
                            int showAll = scanner.nextInt();
                            switch (showAll){
                                case 1:

                                    System.out.println("1. Sort By Customer Code");
                                    System.out.println("2. Sort By Customer Name");
                                    System.out.println("3. Sort By Customer Last Name");
                                    System.out.print("----> ");
                                    int sortCus = scanner.nextInt();

                                    switch (sortCus){
                                        case 1:
                                            customerService.showAllCustomer();
                                            break;

                                        case 2:
                                            customerService.sortCustomersByName();
                                            break;

                                        case 3:
                                            customerService.sortCustomersByLastName();
                                            break;
                                    }
                                    break;

                                case 2:

                                    System.out.println("1. Sort By Account Number");
                                    System.out.println("2. Sort By Customer Code");
                                    System.out.print("----> ");

                                    int sortAcc = scanner.nextInt();

                                    switch (sortAcc){
                                        case 1:
                                            accountService.showAllAccount();
                                            break;

                                        case 2:
                                            accountService.sortAccountsByCustomerCode();
                                            break;
                                    }
                                    break;
                            }
                            break;

                        case 4:

                            System.out.println("Please Insert Account Number:");
                            int accNum = scanner.nextInt();

                            System.out.println("Please Insert Your Amount For Deposite:");
                            long amount = scanner.nextLong();

                            accountService.deposit(accNum , amount);

                            break;

                        case 5:

                            System.out.println("Please Insert Account Number:");
                            int accNum2 = scanner.nextInt();

                            System.out.println("Please Insert Your Amount For Withdraw:");
                            long amount2 = scanner.nextLong();

                            accountService.withdraw(accNum2 , amount2);
                            break;

                        case 6:

                            customerService.customersAccount();
                            break;

                        case 7:

                            System.out.println("Please Insert Account Number For Show Transactions:");
                            int accNum1 = scanner.nextInt();
                            bank.showTransactionAccount(accNum1);
                            break;

                        case 8:

                            System.out.println("1. Search Customer By Code");
                            System.out.println("2. Search Account By AccountNumber");
                            System.out.print("----> ");
                            int search = scanner.nextInt();
                            switch (search){
                                case 1:
                                    customerService.findByCode();
                                    break;
                                case 2:
                                    accountService.findByAccountNumber();
                                    break;
                            }
                            break;

                        case 9:

                            System.out.println("Please Insert Account Number Source :");
                            int source = scanner.nextInt();

                            if (bank.checkAccNumber(source) != 0){

                                System.out.println("Please Insert Account Number Purpose :");
                                int purpose = scanner.nextInt();

                                if (bank.checkAccNumber(purpose) != 0 && source != purpose){

                                    System.out.println("Please Insert Money Amount For Transfer :");
                                    long amounttransfer = scanner.nextLong();
                                    accountService.moneyTransfer(source , purpose , amounttransfer);

                                    break;
                                }
                                else {
                                    System.out.println("Purpose Account Not Found");
                                    break;
                                }
                            }
                            else {
                                System.out.println("Source Account Not Found");
                                break;
                            }


                        case 10:
                            System.exit(0);
                    }
                }
                else {
                    System.out.println("Error : Please Enter keys 1 to 10");
                }

            } catch (InputMismatchException e) {
                System.err.println("Please Just Enter a Number");
                menu();
            }
        }

    }

    ////////  Create New Customer /////////////////////////////////////////

    public Customer createNewCustomer() {

        Customer customer = null;

        System.out.println("Please Insert Customer's Name: ");
        String name = scanner.nextLine();
        name = name.trim();
        name = name.toUpperCase();

        int validName = validation.customerName(name);

        if (validName == 0){

            System.out.println("Please Insert Customer's Last Name: ");
            String lname = scanner.nextLine();

            lname = lname.trim();
            lname = lname.toUpperCase();

            int validLName = validation.customerName(lname);

            if (validLName == 0){
                customer = new Customer(name, lname);
            }
            else {
                menu();
            }
        }
        else {
            menu();
        }

        return customer;
    }

    //////// Create New Account /////////////////////////////////////////

    public Account createNewAccount(){

        System.out.println("Please Insert Customer Code For Add Account :");
        int id = scanner.nextInt();

        int code = bank.checkCustomer(id);

        Account account = null;

        if (code != 0){

            System.out.println("Please Insert First Balance: ");
            long balance = scanner.nextLong();

            AccountType accountType = accountType();

            account = new Account(balance , code , accountType);

        }
        else {
            System.out.println("User Not Found");
            menu();
        }
        return account;
    }

    ///////  Account Type ///////////////////////////////////////////////////

    public AccountType accountType (){
        System.out.println("Please Insert Account Type :");
        System.out.println("1.Long_Term");
        System.out.println("2.Short_Term");
        System.out.println("3.Gharzolhasane");
        System.out.print("----> ");
        int accTypeSwitch = scanner.nextInt();
        AccountType accountType = null;

        if (accTypeSwitch > 0 && accTypeSwitch < 4){
            switch (accTypeSwitch){
                case 1:
                    accountType = AccountType.Long_Term;
                    break;
                case 2:
                    accountType =  AccountType.Short_Term;
                    break;
                case 3:
                    accountType =  AccountType.Gharzolhasane;
                    break;
            }
            return accountType;
        }
        else {
            System.out.println("Please Insert Key 1 to 3 ");
        }
        return accountType();
    }

}
