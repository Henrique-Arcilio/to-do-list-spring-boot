package com.henrique.to_do_list.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(length = 400, nullable = true)
    private String description;

    @OneToMany(mappedBy = "toDoList") //saying that it's not a column
    private List<Task> Tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;


}
