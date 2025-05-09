package com.henrique.to_do_list.Controller;


import com.henrique.to_do_list.Exception.Registration.PasswordDoesntMatchException;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Service.RegistrationValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final RegistrationValidationService validationService;

    public RegistrationController(RegistrationValidationService validationService){
        this.validationService = validationService;
    }


    @PostMapping("/save-account")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam String passwordConfirm, Model model){
        try{
            validationService.isPasswordValid(user, passwordConfirm);

        }catch (PasswordDoesntMatchException exception){
            model.addAttribute("error", exception.getMessage());
            return "create-account";
        }
        return "redirect:/login";
    }
}
