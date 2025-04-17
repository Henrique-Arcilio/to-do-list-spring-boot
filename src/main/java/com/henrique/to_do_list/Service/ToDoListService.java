package com.henrique.to_do_list.Service;

import com.henrique.to_do_list.Model.ToDoList;
import com.henrique.to_do_list.Model.User;
import com.henrique.to_do_list.Repository.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {
    private final ToDoListRepository repository;

    public ToDoListService(ToDoListRepository repository) {
        this.repository = repository;
    }

    public ToDoList createToDoList(String name, String description, User user){
        ToDoList toDoList = new ToDoList();
        toDoList.setName(name);
        toDoList.setDescription(description);
        toDoList.setUser(user);
        repository.save(toDoList);
        return toDoList;
    }

    public List<ToDoList> getAllToDoListsFromUser(User user){
        return repository.findAllByUser(user);
    }


}
