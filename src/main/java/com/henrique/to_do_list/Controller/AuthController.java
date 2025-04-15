package com.henrique.to_do_list.Controller;

import com.henrique.to_do_list.Exception.Authentication.AuthenticationException;
import com.henrique.to_do_list.Service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private AuthenticationService authenticator;

    public AuthController(AuthenticationService authenticator) {
        this.authenticator = authenticator;
    }

    @PostMapping("/login")
    public String signInUser(@RequestParam String email, @RequestParam String password,
                             Model model){
        try{
            authenticator.Authenticate(email, password);
        }
        catch (AuthenticationException exception){
            model.addAttribute("error", exception.getMessage());
        }
        return "redirect:home";
    }
}
