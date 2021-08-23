package com.gj.ticketbooker;

import com.gj.ticketbooker.runner.AsyncRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TicketBookerApplication {

	@Autowired
	AsyncRunner asyncRunner;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TicketBookerApplication.class, args);
		AsyncRunner asyncRunner = (AsyncRunner)context.getBean("asyncRunner");

		asyncRunner.start();


	}

}
