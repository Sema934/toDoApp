package com.example.toDoApp.domain;


import com.example.toDoApp.dto.TaskDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.time.LocalDate;

@Getter
@Setter
@Document
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    @Field
    private String title;
    @Field
    private String context;
    @Field
    private String dueDate;
    @Field
    private String status;
    @Field
    private User user;

    public TaskDTO toDTO() {
        TaskDTO dto = new TaskDTO();
        dto.setId(getId());
        dto.setDueDate(getDueDate());
        dto.setContext(getContext());
        dto.setStatus(getStatus());
        dto.setTitle(getTitle());
        if (getUser() != null)
            dto.setUser(getUser().toDTO());

        return dto;
    }
}

