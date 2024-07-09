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

import com.printit.dto.AdminRequest;
import com.printit.dto.AdminResponse;
import com.printit.dto.ResponseStructure;
import com.printit.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(@Valid @RequestBody AdminRequest adminRequest,HttpServletRequest request){
		return adminService.saveAdmin(adminRequest, request);
	}
	@GetMapping("/activate")
	public String activateAccount(@RequestParam String token) {
		return adminService.activateAccount(token);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@Valid @RequestBody AdminRequest adminRequest,@PathVariable(name = "id") long id){
		return adminService.updateAdmin(adminRequest, id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findById(@PathVariable(name = "id") long id){
		return adminService.findById(id);
	}
	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<AdminResponse>> verify(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password){
		return adminService.verify(email, password);
	} 
	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<AdminResponse>> verify(@RequestParam(value = "phone") long phone,@RequestParam(value = "password") String password){
		return adminService.verify(phone, password);
	} 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable(name = "id") long id){
		return adminService.deleteAdmin(id);
	}

}
