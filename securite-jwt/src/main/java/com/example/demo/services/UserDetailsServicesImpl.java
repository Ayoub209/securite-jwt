package com.example.demo.services;

import java.util.ArrayList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Appuser;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {
    
	@Autowired
	AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Appuser user=accountService.findUserByUsername(username);
		if(user==null) throw new UsernameNotFoundException(username);
		
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		user.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRole()));
		});
		return new User(user.getUsername(),user.getPassword(),authorities);
	}
	

}
