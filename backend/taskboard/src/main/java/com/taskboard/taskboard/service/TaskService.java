package com.taskboard.taskboard.service;

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
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // ---------------- Read ----------------
    public List<Task> getAllTasks(Status status, Priority priority) {

        if (status != null && priority != null) {
            return taskRepository.findByStatusAndPriority(status, priority);
        }

        if (status != null) {
            return taskRepository.findByStatus(status);
        }

        if (priority != null) {
            return taskRepository.findByPriority(priority);
        }

        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    // ---------------- Update ----------------
    public Task updateTask(Long id, Task updatedTask) {
        Task existing = getTaskById(id);

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setPriority(updatedTask.getPriority());
        existing.setStatus(updatedTask.getStatus());
        existing.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existing);
    }

    public Task updateStatus(Long id, Status status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    // ---------------- Delete ----------------
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }
}
