package com.henrique.to_do_list.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 400, nullable = true)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false)
    private LocalDate startDate = LocalDate.now();

    @Column(nullable = true)
    private LocalDate endDate;

    @Column(nullable = true)
    private LocalDate completionDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityLevel priority;

    @ManyToOne
    @JoinColumn(name = "fk_todolist")
    private ToDoList toDoList;
}
