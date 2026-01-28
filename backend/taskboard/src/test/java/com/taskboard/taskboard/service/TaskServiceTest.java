package com.taskboard.taskboard.service;

import com.taskboard.taskboard.dto.TaskRequestDTO;
import com.taskboard.taskboard.dto.TaskResponseDTO;
import com.taskboard.taskboard.model.Priority;
import com.taskboard.taskboard.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    void shouldCreateAndFetchTaskSuccessfully() {

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Test Task");
        dto.setDescription("Service test");
        dto.setPriority(Priority.HIGH);
        dto.setStatus(Status.TODO);

        TaskResponseDTO created = taskService.createTask(dto);

        TaskResponseDTO fetched = taskService.getTaskById(created.getId());

        assertThat(fetched.getTitle()).isEqualTo("Test Task");
        assertThat(fetched.getStatus()).isEqualTo(Status.TODO);
    }
}
