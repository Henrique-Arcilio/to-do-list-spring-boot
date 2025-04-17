package com.henrique.to_do_list.Controller;

import com.henrique.to_do_list.Model.ToDoList;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Model.UserSession;
import com.henrique.to_do_list.Service.ToDoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserHomeController {

    private final UserSession userSession;
    private final ToDoListService toDoListService;

    public UserHomeController(UserSession userSession, ToDoListService toDoListService) {
        this.userSession = userSession;
        this.toDoListService = toDoListService;
    }

    @PostMapping("/home/create-new-list")
    public String createList(@RequestParam String name, @RequestParam String description, Model model){
        if(userSession.isLogado()){
            User user = userSession.getUser();
            toDoListService.createToDoList(name, description, user);
        }
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String showHome(Model model){
        if(userSession.isLogado()){
            User user = userSession.getUser();
            List<ToDoList> userToDoLists = toDoListService.getAllToDoListsFromUser(user);
            model.addAttribute("userTodoLists", userToDoLists);
            return "home";
        }
        return "redirect:/login";
    }


}
