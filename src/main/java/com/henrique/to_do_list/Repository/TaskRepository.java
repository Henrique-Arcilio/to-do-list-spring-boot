package com.henrique.to_do_list.Repository;

import com.henrique.to_do_list.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
