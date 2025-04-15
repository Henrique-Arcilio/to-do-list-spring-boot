package com.henrique.to_do_list.Exception.Authentication;

public class UnknownEmailException extends AuthenticationException {
    public UnknownEmailException(String message) {
        super(message);
    }
}
