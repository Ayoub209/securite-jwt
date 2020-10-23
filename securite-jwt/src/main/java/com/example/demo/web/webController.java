package com.example.demo.web;

import java.util.List
;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.TaskRepository;
import com.example.demo.entities.Task;

@RestController
public class webController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	
	@GetMapping("/task")
	public List<Task> listTask(){
		return taskRepository.findAll();
	}
		
	@PostMapping("/saveTask")
	public Task save(@RequestBody Task t) {
		return taskRepository.save(t);
	}

}
