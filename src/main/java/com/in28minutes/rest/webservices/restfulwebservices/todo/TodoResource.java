package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	
	private TodoService service;
	
	public TodoResource(TodoService service) {
		this.service=service;
	}
	
	@GetMapping("users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username){
		
		return  service.findByUsername(username);
		
	}
	
	@PostMapping("users/{username}/todos")
	public Todo createTodo(@PathVariable String username,@RequestBody Todo todo){
		
		return service.addTodo(todo.getUsername(), todo.getDescription(), todo.getTargetDate(), false);
		
	}
	
	@GetMapping("users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id){
		return  service.findById(id);
		
	}
	
	@DeleteMapping("users/{username}/todos/{id}")
	public ResponseEntity<Void>  deleteTodo(@PathVariable String username, @PathVariable int id){

       service.deleteById(id);
       
       return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){

       Todo findTodo = service.findById(id);
       
       
       if(findTodo==null) {
    	   ResponseEntity.notFound().build();
    	   return null;
       }
    	  
       service.updateTodo(todo);
       return todo;
		
	}
	
	
	
	

}
