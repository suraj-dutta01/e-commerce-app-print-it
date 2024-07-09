package com.printit.dto;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminRequest {
	@NotBlank(message = "Name is Mendatory")
	private String name;
	@Email(message = "Invalid Format")
	private String email;
	@NotBlank(message = "Password is Mendatory")
	@Size(min = 6,max = 26,message = "Password Length between 6 to 26")
	private String password;
	private long phone;
}
