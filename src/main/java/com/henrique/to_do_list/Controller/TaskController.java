package com.henrique.to_do_list.Controller;

import com.henrique.to_do_list.Model.PriorityLevel;
import com.henrique.to_do_list.Model.ToDoList;
import com.henrique.to_do_list.Repository.ToDoListRepository;
import com.henrique.to_do_list.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TaskController {

    private final ToDoListRepository toDoListRepository;
    private final TaskService taskService;

    public TaskController(ToDoListRepository toDoListRepository, TaskService taskService) {
        this.toDoListRepository = toDoListRepository;
        this.taskService = taskService;
    }

    @GetMapping("/home/edit/{id}")
    public String showEdit(@PathVariable UUID id, Model model){
        Optional<ToDoList> opToDoList = toDoListRepository.findById(id);
        if(opToDoList.isPresent()){
            ToDoList toDoList = opToDoList.get();
            model.addAttribute("toDoList", toDoList);
        }
        return "tasks-page";
    }
    @PostMapping("/home/edit/{id}/create-task")
    public String createTask(@PathVariable UUID id, @RequestParam String name, @RequestParam String description,
                           @RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam PriorityLevel priority){
        System.out.println("entrou aqui");
        taskService.createTask(id, name, description, startDate, endDate, priority);
        return "redirect:/home/edit/" + id;
    }
}
