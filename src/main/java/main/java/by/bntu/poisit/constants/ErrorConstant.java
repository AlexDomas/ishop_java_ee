/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.constants;

public class ErrorConstant {
    public static final int INCORRECT_LOGIN = 1;
    public static final int INCORRECT_PASSWORD = 2;
    public static final int INCORRECT_FIRSTNAME = 3;
    public static final int INCORRECT_LASTNAME = 4;
    
    public static final int INCORRECT_PRODUCT_ID = 1;
    public static final int INCORRECT_PRODUCT_NAME = 2;
    public static final int INCORRECT_DESCRIPTION = 3;
    public static final int INCORRECT_PRICE = 4;
    public static final int CORRECT_INFO = 5;
    
    public static final int INCORRECT_ID = 1;
    
    
    public static final String STR_INCORRECT_PRODUCT_ID = "\nAn error occurred when "
            + "entering the product code, you must enter an integer value in the range from 1 to 6 characters;\n ";
    public static final String STR_INCORRECT_PRODUCT_NAME = "\nThe name of the product must be "
            + "from 5 to 20 characters, including only Latin, numbers, punctuation marks («.», «,», «-», «/»);\n ";
    public static final String STR_INCORRECT_DESCRIPTION = "\nDescription must be between 1 and 60 characters;\n ";
    public static final String STR_INCORRECT_PRICE = "\nPrice must be between 2 and 9 characters, including numbers only; \n";
    public static final String STR_NO_ID = "\nThere is no product with this identifier; \n";
    public static final String STR_NO_USER_WITH_ID = "\nThere is no user with this identifier; \n";
    public static final String STR_REPEAT_IN_SYSTEM = "\nThe system already has an identifier or product name that you entered; \n";
    public static final String STR_NO_PRODUCT_WITH_ID = "\nNo products with this ID; \n";
    public static final String STR_INCORRECT_ID = "\nAn error occurred when "
            + "entering the product code, you must enter an integer value in the range from 1 to 6 characters;\n ";
    
    public static final String STR_CORRECT_INFO = "\nNo errors. Product was added.\n ";
    
    public static final String STR_CORRECT_DELETE = "\nNo errors. Product was deleted.\n ";
    
    public static final String STR_CORRECT_DELETE_USER = "\nNo errors. User was deleted.\n ";
}
