package com.pss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.statemachine.StateMachine;

import com.pss.enums.Events;
import com.pss.enums.States;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class SpringbootfreemarkApplication implements CommandLineRunner {
	
	@Autowired  
	private StateMachine<States, Events> stateMachine;  
	 
	public static void main(String[] args) {
		SpringApplication.run(SpringbootfreemarkApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}
	
	
}
