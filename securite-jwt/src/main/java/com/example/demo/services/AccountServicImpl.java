package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ApproleRepository;
import com.example.demo.dao.AppuserRepository;
import com.example.demo.entities.Approle;
import com.example.demo.entities.Appuser;

@Service
@Transactional
public class AccountServicImpl implements AccountService {

	@Autowired
	private AppuserRepository appuserRepository;
	@Autowired
	private ApproleRepository approleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Appuser saveUser(Appuser user) {
		String hach=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hach);
		return appuserRepository.save(user);
	}

	@Override
	public Approle saveRole(Approle role) {
		return approleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		Approle role=approleRepository.findByRole(roleName);
		Appuser user=appuserRepository.findByUsername(username);
		user.getRoles().add(role);
		
	}

	@Override
	public Appuser findUserByUsername(String username) {
		return appuserRepository.findByUsername(username);
	}

}
