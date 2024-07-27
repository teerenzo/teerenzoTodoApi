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
public class TodoJpaResource {
	
	private TodoRepository todoRepository;
	
	public TodoJpaResource(TodoRepository todoRepository) {
		this.todoRepository=todoRepository;
	}
	
	@GetMapping("users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username){
		
		return  todoRepository.findByUsername(username);
		
	}
	
	@PostMapping("users/{username}/todos")
	public Todo createTodo(@PathVariable String username,@RequestBody Todo todo){
		
		return todoRepository.save(todo);
		
	}
	
	@GetMapping("users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id){
		return  todoRepository.findById(id).get();
		
	}
	
	@DeleteMapping("users/{username}/todos/{id}")
	public ResponseEntity<Void>  deleteTodo(@PathVariable String username, @PathVariable int id){

		todoRepository.deleteById(id);
       
       return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){

       Todo findTodo = todoRepository.findById(id).get();
       
       
       if(findTodo==null) {
    	   ResponseEntity.notFound().build();
    	   return null;
       }
    	  
       todoRepository.save(todo);
       return todo;
		
	}
	
	
	
	

}
