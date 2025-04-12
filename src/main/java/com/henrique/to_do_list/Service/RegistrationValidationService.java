package com.henrique.to_do_list.Service;

import com.henrique.to_do_list.Exception.Registration.PasswordDoesntMatchException;
import com.henrique.to_do_list.Model.User;
import org.springframework.stereotype.Service;

@Service
public class RegistrationValidationService {
    public void isPasswordValid(User user, String passwordConfirm) throws PasswordDoesntMatchException {
        String password = user.getPassword();
        if (password != null) {
            if(!password.equals(passwordConfirm)){
                throw new PasswordDoesntMatchException("The passwords doesnt match");
            }
        }
    }
}
