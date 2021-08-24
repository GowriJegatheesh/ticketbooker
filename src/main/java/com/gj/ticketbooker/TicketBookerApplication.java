package com.gj.ticketbooker;

import com.gj.ticketbooker.runner.AsyncRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/*
The main class and entry point to ticketbooker application
 */
@SpringBootApplication
public class TicketBookerApplication {

	@Autowired
	AsyncRunner asyncRunner;

	/**
	 * Main method to start the application
	 *
	 * @param args commandline arguments for the main method
	 */
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TicketBookerApplication.class, args);
		AsyncRunner asyncRunner = (AsyncRunner)context.getBean("asyncRunner");

		//Start the async runner to process simultaneous requests
		asyncRunner.start();


	}

}
