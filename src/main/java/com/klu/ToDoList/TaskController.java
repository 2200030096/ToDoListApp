package com.klu.ToDoList;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class TaskController {
	
	@Autowired
	TaskDAOImpl trd;
	
	@Autowired
	private EmailService ems;
	
	@Autowired
	private TaskRepositary trs;
	
	@Autowired
	private TaskReminderService trrs;
	
	  @Autowired
	    private QuoteService quoteService;
	
	@GetMapping("/")
	public String homepage()
	{
		return "home";
	}
	
	@PostMapping("/insert")
	public String addtask(@ModelAttribute Task t)
	{
		trd.createTask(t);
		return "sucess";
	}
	  @GetMapping("/add")
	    public String showAddTaskPage() {
	        // Return the name of the JSP file (excluding .jsp)
	        return "addtask";
	    }
	  @GetMapping("/viewall")
	  public String viewTasks(Model model) {
	      List<Task> tasks = trd.viewAllTask();
	      if (tasks.isEmpty()) {
	          model.addAttribute("message", "No tasks available.");
	      }
	      model.addAttribute("tasks", tasks);
	      return "viewalltasks";
	  }
	  
	   @GetMapping("/deleteTaskPage")
	  public String deleteTask(Model model) {
	      List<Task> tasks = trd.viewAllTask();
	      if (tasks.isEmpty()) {
	          model.addAttribute("message", "No tasks available.");
	      }
	      model.addAttribute("tasks", tasks);
	      return "deletetask";
	  }
	   @PostMapping("/deleteTask")
	    public String deleteTask(@RequestParam("id") int id, Model model) {
	        trd.deleteTask(id);  // Delete the task
	        // After deletion, refresh the list on the deleteTaskPage.
	        List<Task> tasks = trd.viewAllTask();
	        if (tasks.isEmpty()) {
	            model.addAttribute("message", "No tasks available.");
	        }
	        model.addAttribute("tasks", tasks);
	        return "deletetask";  // Return to the deleteTaskPage with updated task list
	    }
	   
	  @PostMapping("/updateTask")
	  public String updateTask()
	  {
		  return "";
	  }
      
	  @PostMapping("/tasks")
	  public ResponseEntity<String> createTask(@RequestBody Task task) {
	      trs.save(task);
	      if (task.getDueDate().equals(LocalDate.now().plusDays(1)) && task.getUserEmail() != null) {
	          ems.sendEmail(task);
	      }
	      return ResponseEntity.ok("Task created successfully");
	  }

////	  
//	  @GetMapping("/test-send-reminders")
//	  public ResponseEntity<String> testSendReminders() {
//	      
//	      return ResponseEntity.ok("Reminder emails sent immediately");
//	  }
	  
	  @GetMapping("/test-send-reminders")
	  public ResponseEntity<String> testSendReminders() {
	      trrs.sendEmailsForTasksDueTomorrow();
	      return ResponseEntity.ok("Reminder emails sent immediately");
	  }
	  @GetMapping("/random-quote")
	  @ResponseBody
	  public String getRandomQuote() {
	      return quoteService.getRandomQuote();  // Fetch quote from service and return it as plain text
	  }

     
	  @GetMapping("/search")
	    public String searchTasks(@RequestParam String title, Model model) {
	        List<Task> tasks = trd.searchByTitle(title);
	        model.addAttribute("tasks", tasks);
	        return "search"; // Looks for search.jsp in the views directory
  
	 
	  }
	  @PostMapping("/ask")
	  @ResponseBody
	  public String getResponse(@RequestBody String userInput) {
	      String input = userInput.toLowerCase();

	      // Chatbot logic with multiple responses
	      if (input.contains("hello") || input.contains("hi")|| input.contains("hey"))  {
	          return "Hello! How can I assist you today?";
	      } else if (input.contains("task")) {
	          return "You can add, view, update, or delete tasks using the options in the menu.";
	      } else if (input.contains("quote")) {
	          return "Click on 'Generate Quote' to receive an inspirational quote.";
	      } else if (input.contains("email")) {
	          return "We send reminder emails for tasks due tomorrow. Ensure your email is configured!";
	      } else if (input.contains("reminder")) {
	          return "Reminders for tasks due tomorrow are sent automatically.";
	      } else if (input.contains("help")) {
	          return "Sure! You can ask about tasks, quotes, or reminders.";
	      } else if (input.contains("due date")) {
	          return "You can set due dates while creating or updating a task.";
	      } else if (input.contains("delete")) {
	          return "To delete a task, use the 'Delete Task' option in the menu.";
	      } else if (input.contains("view tasks")) {
	          return "Click on 'View Tasks' to see all your tasks.";
	      } else if (input.contains("update")) {
	          return "To update a task, use the 'Update Task' option in the menu.";
	      } else if (input.contains("how are you")) {
	          return "I'm just a bot, but I'm here to help you!";
	      } else if (input.contains("bye") || input.contains("goodbye")) {
	          return "Goodbye! Have a great day!";
	      } else if (input.contains("thank you") || input.contains("thanks")) {
	          return "You're welcome! Let me know if you need anything else.";
	      } else if (input.contains("what is this")) {
	          return "This is a To-Do List application to help you manage your tasks efficiently.";
	      } else {
	          return "I'm sorry, I didn't understand that. Can you please rephrase?";
	      }
	  }


}
