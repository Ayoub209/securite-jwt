package com.example.demo.dao.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Appuser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;

	public JwtAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Appuser appuser=null;
		try {
			appuser=new ObjectMapper().readValue(request.getInputStream(), Appuser.class);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("username="+appuser.getUsername());
		System.out.println("password="+appuser.getPassword());
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appuser.getUsername(), appuser.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User springUser=(User) authResult.getPrincipal();
		String jwt=Jwts.builder()
                  .setSubject(springUser.getUsername())
                  .setExpiration(new Date(System.currentTimeMillis()+SecurityConstant.EXPIRATION_TIME))
                  .signWith(SignatureAlgorithm.HS256, SecurityConstant.SECRET)
                  .claim("roles", springUser.getAuthorities())
		          .compact();
		
		          response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX+jwt);          
		
		//super.successfulAuthentication(request, response, chain, authResult);
	}
}
