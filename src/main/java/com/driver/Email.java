package com.driver;

import java.util.*;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        boolean isValidate = true;
        if(oldPassword.isEmpty() || newPassword.isEmpty()) {
            System.out.println("Email Id or Password should not be empty");
            isValidate = false;
        }
        if(!oldPassword.equals(this.password)) {
            System.out.println("Old Password not matched with current Password");
            isValidate = false;
        }
        // 1. It contains at least 8 characters
        if(newPassword.length() < 8) {
            System.out.println("New Password '"+newPassword+"' length must be at least 8");
            isValidate = false;
        }
        // 2. It contains at least one uppercase letter
        if(!isUppercaseFound(newPassword)) {
            System.out.println("New Password '"+newPassword+"' Should be at least one uppercase");
            isValidate = false;
        }

        // 3. It contains at least one lowercase letter
        if(!isLowercaseFound(newPassword)) {
            System.out.println("New Password '"+newPassword+"' Should be at least one lowercase");
            isValidate = false;
        }

        // 4. It contains at least one digit
        if(!isDigitFound(newPassword)) {
            System.out.println("New Password '"+newPassword+"' Should be at least one digit");
            isValidate = false;
        }
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(!isSpecialCharFound(newPassword)) {
            System.out.println("New Password '"+newPassword+"' Should be at least one special shar");
            isValidate = false;
        }
        if(!isValidate) return;
        this.password = newPassword;
        System.out.println("New Password has been updated!");
        return;
    }

    public boolean isUppercaseFound(String password) {
        boolean isUppercase = false;
        for (char ch: password.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                isUppercase = true;
                break;
            }
        }
        return isUppercase;
    }

    public boolean isLowercaseFound(String password) {
        boolean isLowercase = false;
        for (char ch: password.toCharArray()) {
            if(Character.isLowerCase(ch)) {
                isLowercase = true;
                break;
            }
        }
        return isLowercase;
    }

    public boolean isDigitFound(String password) {
        boolean isDigit = false;
        for (char ch: password.toCharArray()) {
            if(Character.isDigit(ch)) {
                isDigit = true;
                break;
            }
        }
        return isDigit;
    }

    public boolean isSpecialCharFound(String password) {
        boolean isSpecialChar = false;
        for (char ch: password.toCharArray()) {
            if(Character.isSpaceChar(ch)) {
                isSpecialChar = true;
                break;
            }
        }
        return isSpecialChar;
    }
}
