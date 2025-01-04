package com.klu.ToDoList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    TaskRepositary tr;

    @PersistenceContext
    EntityManager em;

    @Override
    public String createTask(Task t) {
        tr.save(t);
        return "Successfully inserted";
    }

    @Override
    public List<Task> viewAllTask() {
        return tr.findAll();
    }

    @Override
    public String updateTask(int id, Task t) {
        return null;
    }
    @Override
    @Transactional
    public String deleteTask(int id) {
        // Delete the task by ID
        tr.deleteById(id);

        // Create a temporary table to renumber IDs
        em.createNativeQuery(
            "CREATE TEMPORARY TABLE temp_tasks AS SELECT * FROM tasks ORDER BY id;"
        ).executeUpdate();

        // Truncate the original table to clear it
        em.createNativeQuery("TRUNCATE TABLE tasks;").executeUpdate();

        // Reinsert rows with renumbered IDs
        em.createNativeQuery(
            "INSERT INTO tasks (id, title, description, due_date) " +
            "SELECT ROW_NUMBER() OVER (ORDER BY id), title, description, due_date FROM temp_tasks;"
        ).executeUpdate();

        // Drop the temporary table
        em.createNativeQuery("DROP TEMPORARY TABLE temp_tasks;").executeUpdate();

        // Reset AUTO_INCREMENT to the next available ID
        em.createNativeQuery("ALTER TABLE tasks AUTO_INCREMENT = 1;").executeUpdate();

        return "Task deleted successfully and IDs renumbered";
    }
        
        public List<Task> searchByTitle(String title) {
            return tr.findByTitleContainingIgnoreCase(title);
        
        
    }

}