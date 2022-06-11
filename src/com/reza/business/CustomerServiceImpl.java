package com.reza.business;

import com.reza.console.Console;
import com.reza.database.BankDB;
import com.reza.entity.Customer;

import java.util.Scanner;

public class CustomerServiceImpl implements CustomerService {

    Scanner scanner = new Scanner(System.in);
    BankDB bank = new BankDB();
    Console console = new Console();

    @Override
    public void addCustomer() {

        Customer customer = console.createNewCustomer();
        bank.addNewCustomer(customer);
    }

    @Override
    public void showAllCustomer() {
        bank.getAllCustomer();
    }

    @Override
    public void findByCode() {

        System.out.println("Please Insert Code For Search:");
        int id = scanner.nextInt();
        bank.getCustomerByCode(id);
    }

    @Override
    public void sortCustomersByName() {
        bank.sortCustomerByCustomerName();
    }

    @Override
    public void sortCustomersByLastName() {
        bank.sortCustomersByCustomerLastName();
    }

    @Override
    public void customersAccount() {
        System.out.println("Please Insert Customer's Code:");
        int code = scanner.nextInt();
        bank.customersAccount(code);
    }
}
