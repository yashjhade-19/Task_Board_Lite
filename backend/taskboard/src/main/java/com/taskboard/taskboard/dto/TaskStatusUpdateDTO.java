package com.taskboard.taskboard.dto;

import com.taskboard.taskboard.model.Status;
import jakarta.validation.constraints.NotNull;

public class TaskStatusUpdateDTO {

    @NotNull
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
