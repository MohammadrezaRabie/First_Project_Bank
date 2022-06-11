package com.reza.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralValidation {

    public int customerName(String name) {

        Pattern pattern = Pattern.compile("[^a-zA-Z ]");
        Matcher matcher = pattern.matcher(name);

        if (matcher.find()){
            System.err.println("Please Insert Your Name And Last Name Without Number & Character");
            return 1;
        }
        else {
            return 0;
        }
    }
}
