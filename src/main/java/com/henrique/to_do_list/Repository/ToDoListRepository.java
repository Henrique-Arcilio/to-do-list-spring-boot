package com.henrique.to_do_list.Repository;

import com.henrique.to_do_list.Model.ToDoList;
import com.henrique.to_do_list.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ToDoListRepository extends JpaRepository<ToDoList, UUID> {
    List<ToDoList> findAllByUser(User user);
}
