package com.henrique.to_do_list.Exception.Authentication;

public class IncorrectPasswordException extends AuthenticationException {
  public IncorrectPasswordException(String message) {
    super(message);
  }
}
