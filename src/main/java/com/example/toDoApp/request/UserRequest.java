package com.example.toDoApp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String surname;
    private String userName;
    private String password;
    private String email;
}
