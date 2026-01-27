package com.taskboard.taskboard.controller;

import com.taskboard.taskboard.model.Priority;
import com.taskboard.taskboard.model.Status;
import com.taskboard.taskboard.model.Task;
import com.taskboard.taskboard.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    // Constructor injection
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ---------------- Create ----------------
    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    // ---------------- Read ----------------
    @GetMapping
    public List<Task> getAllTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority
    ) {
        return taskService.getAllTasks(status, priority);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // ---------------- Update ----------------
    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task task
    ) {
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}/status")
    public Task updateStatus(
            @PathVariable Long id,
            @RequestParam Status status
    ) {
        return taskService.updateStatus(id, status);
    }

    // ---------------- Delete ----------------
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
