package com.printit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.printit.dto.ResponseStructure;
import com.printit.dto.UserRequest;
import com.printit.dto.UserResponse;
import com.printit.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@Valid @RequestBody UserRequest userRequest,HttpServletRequest request){
		return userService.saveUser(userRequest, request);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@Valid @RequestBody UserRequest userRequest,@PathVariable(name = "id") long id){
		return userService.updateUser(userRequest, id);
	}
	@GetMapping("/activate")	
	public String activateAccount(@RequestParam String token) {
		return userService.activateAccount(token);
	}
	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password){
		return userService.verifyUser(email, password);
	}
	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(@RequestParam(value = "phone") long phone,@RequestParam(value = "password") String password){
		return userService.verifyUser(phone, password);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> findById(@PathVariable(name = "id") long id){
		return userService.findById(id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable(name = "id") long id){
		return userService.deleteUser(id);
	}

}
