package com.example.demo;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dao.TaskRepository;
import com.example.demo.entities.Approle;
import com.example.demo.entities.Appuser;
import com.example.demo.entities.Task;
import com.example.demo.services.AccountService;

@SpringBootApplication
public class SecuriteJwtApplication implements CommandLineRunner{
	
    @Autowired
	private TaskRepository taskRepository;
    @Autowired
    private AccountService accountService;
	public static void main(String[] args) {
		SpringApplication.run(SecuriteJwtApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
		accountService.saveUser(new Appuser(null,"admin","1234",null));
		accountService.saveUser(new Appuser(null,"user","12345",null));
		accountService.saveRole(new Approle(null,"ADMIN"));
		accountService.saveRole(new Approle(null,"USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");
		
		Stream.of("T1","T2","T3").forEach(t->{
			taskRepository.save(new Task(null,t));
		});
		taskRepository.findAll().forEach(t->{
			System.out.println(t.getName());
		});
	}
	

}
