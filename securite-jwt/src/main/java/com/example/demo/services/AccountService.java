package com.example.demo.services;

import com.example.demo.entities.Approle;
import com.example.demo.entities.Appuser;

public interface AccountService {
	
	public Appuser saveUser(Appuser user) ;
	public Approle saveRole(Approle role);
	public void addRoleToUser(String username,String role);
    public Appuser findUserByUsername(String username);
}
