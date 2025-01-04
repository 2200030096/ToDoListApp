package com.klu.ToDoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToDoListApplication {
	
	EmailService emms;

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
		System.out.println("heyy");
	}
	 public void sendTestEmail() {
	        emms.sendTestEmail();
	        emms.worn();
	    }
}
