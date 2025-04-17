package com.henrique.to_do_list.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSession {
    private User user;
    private List<ToDoList> tarefas;

    public boolean isLogado(){
        return user != null;
    }
}
