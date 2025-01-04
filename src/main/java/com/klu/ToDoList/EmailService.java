package com.klu.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {
	   @Autowired
	    private JavaMailSender mailSender;
	   
	   private static final Logger logger = LoggerFactory.getLogger(EmailService.class);


	   public void sendEmail(Task task) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(task.getUserEmail()); // Send email to user associated with task
	        message.setSubject("Task Deadline Reminder");
	        message.setText("Reminder: Your task '" + task.getTitle() + "' is due tomorrow, " + task.getDueDate());
	        mailSender.send(message);
	        
	        
}
	   public void sendTestEmail() {
		    try {
		        SimpleMailMessage message = new SimpleMailMessage();
		        message.setTo("2200030096cseh@gmail.com");
		        message.setSubject("Test Email");
		        message.setText("This is a test email from Spring Boot.");
		        mailSender.send(message);
		        logger.info("Test email sent successfully.");
		    } catch (Exception e) {
		        logger.error("Failed to send test email: " + e.getMessage());
		    }
		}
	   public void worn()
	   {
		   System.out.println("hello");
	   }
}