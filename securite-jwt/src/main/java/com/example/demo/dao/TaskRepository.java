package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
