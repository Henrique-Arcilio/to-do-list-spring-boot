package com.henrique.to_do_list.Controller;

import com.henrique.to_do_list.Exception.Authentication.AuthenticationException;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Repository.ToDoListRepository;
import com.henrique.to_do_list.Service.AuthenticationService;
import com.henrique.to_do_list.Model.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserSession  userSession;
    private final AuthenticationService authenticator;
    private final ToDoListRepository toDoListRepository;

    public AuthController(UserSession userSession, AuthenticationService authenticator, ToDoListRepository toDoListRepository) {
        this.userSession = userSession;
        this.authenticator = authenticator;
        this.toDoListRepository = toDoListRepository;
    }

    @PostMapping("/login")
    public String signInUser(@RequestParam String email, @RequestParam String password,
                             Model model){
        try{
            User user = authenticator.authenticate(email, password);
            userSession.setUser(user);
            userSession.setTarefas(toDoListRepository.findAllByUser(user));
        }
        catch (AuthenticationException exception){
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
        return "redirect:/home";
    }
}
