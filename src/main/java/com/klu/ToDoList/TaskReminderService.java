package com.klu.ToDoList;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskReminderService {
	    @Autowired
	    private TaskRepositary tsr;
	    @Autowired
	    private EmailService emailService; // Inject EmailService to send emails

	    @Scheduled(cron = "0 0 9 * * ?") // Cron expression for 9 AM daily
	    public void sendReminderEmails() {
	        sendEmailsForTasksDueTomorrow();
	    }

	    public void sendEmailsForTasksDueTomorrow() {
	        LocalDate tomorrow = LocalDate.now().plusDays(1);
	        List<Task> tasksDueTomorrow = tsr.findByDueDate(tomorrow);
	        for (Task task : tasksDueTomorrow) {
	            emailService.sendEmail(task);
	        }
	    }
}
