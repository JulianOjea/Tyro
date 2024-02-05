package com.test.tyro.controller;

import com.test.tyro.model.User;
import com.test.tyro.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		List<User> users = this.userService.findAll();

		if(users.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getMethodName(@PathVariable("id") long id) {
		Optional<User> userOp = userService.findById(id);
		if(userOp.isPresent()){
			User user = userOp.orElseThrow();
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user){
		if(user.getName() == null || user.getName().isEmpty()){
			return ResponseEntity.badRequest().body("Name is required");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		Optional<User> userOp = userService.findById(id);
		if(userOp.isPresent()){
			User user = userOp.orElseThrow();
			userService.delete(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
		}
		return ResponseEntity.notFound().build();
	}
}
