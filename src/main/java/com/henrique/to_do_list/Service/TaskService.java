package com.henrique.to_do_list.Service;

import com.henrique.to_do_list.Model.PriorityLevel;
import com.henrique.to_do_list.Model.State;
import com.henrique.to_do_list.Model.Task;
import com.henrique.to_do_list.Model.ToDoList;
import com.henrique.to_do_list.Repository.TaskRepository;
import com.henrique.to_do_list.Repository.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;

    public TaskService(TaskRepository taskRepository, ToDoListRepository toDoListRepository) {
        this.taskRepository = taskRepository;
        this.toDoListRepository = toDoListRepository;
    }

    public List<Task> getTasksToDo(UUID listId){
        Optional<ToDoList> opTodoList = toDoListRepository.findById(listId);
        if(opTodoList.isPresent()){
            List<Task> incompleteTasks = new ArrayList<>();
            for(Task task : opTodoList.get().getTasks()){
                if(!task.isCompleted()){
                    incompleteTasks.add(task);
                }
            }
            return incompleteTasks;
        }
        return null;
    }
    public void markTaskAsCompleted(UUID id) {
        Optional<Task> opTask = taskRepository.findById(id);
        if (opTask.isPresent()) {
            Task task = opTask.get();
            task.setCompleted(true);
            task.setCompletionDate(LocalDate.now());
            taskRepository.save(task);
        }
    }
    public void createTask(UUID todoListId, String name, String description, LocalDate startDate, LocalDate endDate, PriorityLevel priorityLevel){
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setState(State.TODO);
        task.setPriority(priorityLevel);

        Optional<ToDoList> opTodoList = toDoListRepository.findById(todoListId);
        if(opTodoList.isPresent()){
            ToDoList toDoList = opTodoList.get();
            task.setToDoList(toDoList);
            taskRepository.save(task);
        }
    }
}
