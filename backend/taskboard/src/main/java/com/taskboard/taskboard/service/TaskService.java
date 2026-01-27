package com.taskboard.taskboard.service;
import com.taskboard.taskboard.dto.TaskRequestDTO;
import com.taskboard.taskboard.dto.TaskResponseDTO;
import com.taskboard.taskboard.exception.ResourceNotFoundException;
import com.taskboard.taskboard.model.Priority;
import com.taskboard.taskboard.model.Status;
import com.taskboard.taskboard.model.Task;
import com.taskboard.taskboard.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor injection (best practice)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // ---------------- Create ----------------
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());

        return mapToResponse(taskRepository.save(task));
    }


    // ---------------- Read ----------------
    public List<TaskResponseDTO> getAllTasks(Status status, Priority priority) {
        List<Task> tasks;

        if (status != null && priority != null)
            tasks = taskRepository.findByStatusAndPriority(status, priority);
        else if (status != null)
            tasks = taskRepository.findByStatus(status);
        else if (priority != null)
            tasks = taskRepository.findByPriority(priority);
        else
            tasks = taskRepository.findAll();

        return tasks.stream().map(this::mapToResponse).toList();
    }


    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found " + id));

        return mapToResponse(task);
    }


    // ---------------- Update ----------------
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {

        Task existing = getTaskEntity(id);

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setPriority(dto.getPriority());
        existing.setStatus(dto.getStatus());
        existing.setDueDate(dto.getDueDate());

        return mapToResponse(taskRepository.save(existing));
    }


    public TaskResponseDTO updateStatus(Long id, Status status) {
        Task task = getTaskEntity(id);
        task.setStatus(status);
        return mapToResponse(taskRepository.save(task));
    }


    // ---------------- Delete ----------------
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id " + id);

        }
        taskRepository.deleteById(id);
    }

    private TaskResponseDTO mapToResponse(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        return dto;
    }

    private Task getTaskEntity(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found " + id));
    }

}
