package com.taskboard.taskboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskboard.taskboard.dto.TaskRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn400WhenTitleIsTooShort() throws Exception {

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Hi"); // ❌ invalid (less than 3 chars)

        mockMvc.perform(
                post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isBadRequest());
    }
}
