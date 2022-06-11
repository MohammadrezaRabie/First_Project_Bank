package com.reza.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private int code;
    private String first_name;
    private String last_name;
    private static int counter = 1000;


    public Customer() {
    }

    public Customer(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.code = counter;
        counter += 100;
    }

    public int getCode() {
        return code;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

//    @Override
//    public String toString() {
//
//        return
//                "code=" + code +
//                " ---> first_name='" + first_name + '\'' +
//                " ---> last_name='" + last_name + '\''+ "\n"
//                ;
//    }

    @Override
    public String toString() {
        return
                "Code = " + code +
                        " -----> First Name = '" + first_name + '\'' +
                        " -----> Last Name = '" + last_name + '\''+"\n";
    }
}
