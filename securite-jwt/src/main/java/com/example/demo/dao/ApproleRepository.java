package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Approle;

public interface ApproleRepository extends JpaRepository<Approle, Long> {

	public Approle findByRole(String role);
}
