package com.henrique.to_do_list.Controller;

import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Model.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private final UserSession userSession;

    public AccountController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String redirectToLogin(){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String showLogin(){
        if(userSession.isLogado()){
            return "redirect:home";
        }
        return "login";
    }

    @GetMapping("/create-account")
    public String showCreateAccount(Model model){
        if(userSession.isLogado()){
            return "redirect:home";
        }
        model.addAttribute("user", new User());
        return "create-account";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

}
