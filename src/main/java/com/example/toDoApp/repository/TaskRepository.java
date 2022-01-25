package com.example.toDoApp.repository;

import com.example.toDoApp.domain.Task;
import com.example.toDoApp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends CrudRepository<Task, String> {
    List<Task> findByTitle(String title);
    List<Task> findByUser_Id(String userId);

}
