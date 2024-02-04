package com.test.tyro.controller;

import com.test.tyro.model.User;
import com.test.tyro.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
//
//    @Autowired UserRepository userRepository;
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        try {
//			List<User> users = new ArrayList<User>();
//
//            userRepository.findAll().forEach(users::add);
//
//			if (users.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(users, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//    }
//
//    @PostMapping("/user")
//	public ResponseEntity<User> createUser(@RequestBody User user) {
//		try {
//			User _user = userRepository
//					.save(new User(user.getName()));
//			return new ResponseEntity<>(_user, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
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

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user){
		if(user.getName() == null || user.getName().isEmpty()){
			return ResponseEntity.badRequest().body("Name is required");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		Optional<User> userOp = userService.findById(id);
		if(userOp.isPresent()){
			User user = userOp.orElseThrow();
			userService.delete(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
		}
		return ResponseEntity.notFound().build();
	}
}
