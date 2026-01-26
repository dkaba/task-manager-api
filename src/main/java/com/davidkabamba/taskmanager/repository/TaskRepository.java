package com.davidkabamba.taskmanager.repository;

import com.davidkabamba.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
