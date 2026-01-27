package com.taskboard.taskboard.controller;

import com.taskboard.taskboard.dto.TaskRequestDTO;
import com.taskboard.taskboard.dto.TaskResponseDTO;
import com.taskboard.taskboard.dto.TaskStatusUpdateDTO;
import com.taskboard.taskboard.model.Priority;
import com.taskboard.taskboard.model.Status;
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
    public TaskResponseDTO createTask(
            @Valid @RequestBody TaskRequestDTO dto) {
        return taskService.createTask(dto);
    }

    // ---------------- Read ----------------
    @GetMapping
    public List<TaskResponseDTO> getAllTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority
    ) {
        return taskService.getAllTasks(status, priority);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // ---------------- Update (FULL) ----------------
    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO dto
    ) {
        return taskService.updateTask(id, dto);
    }

    // ---------------- Update (STATUS ONLY) ----------------
    @PatchMapping("/{id}/status")
    public TaskResponseDTO updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody TaskStatusUpdateDTO dto
    ) {
        return taskService.updateStatus(id, dto.getStatus());
    }

    // ---------------- Delete ----------------
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
