package com.henrique.to_do_list.Service;

import com.henrique.to_do_list.Exception.Registration.PasswordDoesntMatchException;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationValidationService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public RegistrationValidationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void isPasswordValid(User user, String passwordConfirm) throws PasswordDoesntMatchException {
        String password = user.getPassword();
        if (password != null) {
            if(!password.equals(passwordConfirm)){
                throw new PasswordDoesntMatchException("The passwords doesnt match");
            }
        }
        String encriptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encriptedPassword);
        userRepository.save(user);
    }
}
