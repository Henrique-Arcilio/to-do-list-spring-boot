package com.henrique.to_do_list.Controller;

import com.henrique.to_do_list.Model.ToDoList;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Repository.ToDoListRepository;
import com.henrique.to_do_list.Model.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserHomeController {

    private final UserSession userSession;
    private final ToDoListRepository toDoListRepository;

    public UserHomeController(ToDoListRepository toDoListRepository, UserSession userSession) {
        this.toDoListRepository = toDoListRepository;
        this.userSession = userSession;
    }

    @PostMapping("/home/create-new-list")
    public String createList(@RequestParam String name, @RequestParam String description, Model model){
        if(userSession.isLogado()){
            ToDoList toDoList = new ToDoList();
            toDoList.setName(name);
            toDoList.setDescription(description);
            toDoList.setUser(userSession.getUser());
            toDoListRepository.save(toDoList);
        }
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String showHome(Model model){
        if(userSession.isLogado()){
            User user = userSession.getUser();
            List<ToDoList> userToDoLists = toDoListRepository.findAllByUser(user);
            model.addAttribute("userTodoLists", userToDoLists);
            return "home";
        }
        return "redirect:/login";
    }


}
