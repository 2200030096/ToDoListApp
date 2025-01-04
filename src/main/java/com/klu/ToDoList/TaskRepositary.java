package com.klu.ToDoList;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositary extends JpaRepository<Task, Integer>{
	
	List<Task> findByDueDate(LocalDate duedate);
    List<Task> findByTitleContainingIgnoreCase(String title);

}
