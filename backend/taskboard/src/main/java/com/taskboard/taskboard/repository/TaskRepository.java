package com.taskboard.taskboard.repository;

import com.taskboard.taskboard.model.Task;
import com.taskboard.taskboard.model.Priority;
import com.taskboard.taskboard.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByCreatedAtDesc();

    // Find by status
    List<Task> findByStatus(Status status);

    // Find by priority
    List<Task> findByPriority(Priority priority);

    // Find by status and priority
    List<Task> findByStatusAndPriority(Status status, Priority priority);
}
