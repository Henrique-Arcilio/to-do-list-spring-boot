package com.henrique.to_do_list.Exception.Registration;

public class PasswordDoesntMatchException extends Exception {
    public PasswordDoesntMatchException(String message){
        super(message);
    }
}
