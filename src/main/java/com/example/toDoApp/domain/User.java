package com.example.toDoApp.domain;

import com.example.toDoApp.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class User {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    private String userName;
    @Field
    private String password;
    @Field
    private String email;
    @Field
    private List<Task> todos = new ArrayList<>();

    public UserDTO toDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(getId());
        dto.setName(getName());
        dto.setSurname(getSurname());
        dto.setUserName(getUserName());
        dto.setEmail(getEmail());

        return dto;
    }
}
