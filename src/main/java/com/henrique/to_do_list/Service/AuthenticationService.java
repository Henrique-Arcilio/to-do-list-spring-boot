package com.henrique.to_do_list.Service;

import com.henrique.to_do_list.Exception.Authentication.IncorrectPasswordException;
import com.henrique.to_do_list.Exception.Authentication.UnknownEmailException;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User authenticate(String email, String password) throws IncorrectPasswordException, UnknownEmailException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(!passwordEncoder.matches(password, user.getPassword())){
                throw new IncorrectPasswordException("Incorrect Password");
            }
            return user;
        }else {
            throw new UnknownEmailException("This e-mail is not registred");
        }
    }
}
