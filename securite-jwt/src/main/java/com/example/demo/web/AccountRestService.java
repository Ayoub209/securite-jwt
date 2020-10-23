package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Appuser;
import com.example.demo.services.AccountService;

@RestController
public class AccountRestService {
	
	@Autowired
	private AccountService accountServive;
	
	@PostMapping("/register")
	public Appuser Register(@RequestBody RegisterForm reuser) {
		if(!reuser.getPassword().equals(reuser.getRepassword()))
		throw new RuntimeException("you must confirm your password");
		Appuser appuser=accountServive.findUserByUsername(reuser.getUsername());
		if(appuser!=null)
		throw new RuntimeException("Username already exixt");
			
		Appuser user=new Appuser();
		user.setUsername(reuser.getUsername());
		user.setPassword(reuser.getPassword());
	    accountServive.saveUser(user);
	    accountServive.addRoleToUser(reuser.getUsername(), "USER");
	    return user;
	}

}
