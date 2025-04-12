package com.henrique.to_do_list.Controller;


import com.henrique.to_do_list.Exception.Registration.PasswordDoesntMatchException;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Repository.UserRepository;
import com.henrique.to_do_list.Service.RegistrationValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final RegistrationValidationService validationService;

    public RegistrationController(UserRepository userRepository,
                                  RegistrationValidationService validationService){
        this.userRepository = userRepository;
        this.validationService = validationService;
    }


    @PostMapping("/save-account")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam String passwordConfirm, Model model){
        try{
            validationService.isPasswordValid(user, passwordConfirm);
            userRepository.save(user);
        }catch (PasswordDoesntMatchException exception){
            model.addAttribute("error", "Passwords doesn't match" );
            return "create-account";
        }
        return "redirect:/login";
    }
}
