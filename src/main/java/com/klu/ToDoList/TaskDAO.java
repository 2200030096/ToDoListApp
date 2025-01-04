package com.klu.ToDoList;

import java.util.List;

public interface TaskDAO {

	public String createTask(Task t);
	public List<Task> viewAllTask();
	public String updateTask(int id, Task t);
	public String deleteTask(int id);
}
