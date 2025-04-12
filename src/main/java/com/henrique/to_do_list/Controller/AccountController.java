package com.henrique.to_do_list.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/")
    public String redirectToLogin(){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @GetMapping("/create-account")
    public String showCreateAccount(){
        return "create-account";
    }
}
