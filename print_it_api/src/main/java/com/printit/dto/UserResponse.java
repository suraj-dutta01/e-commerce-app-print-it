package com.printit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
	private long id;
	private String name;
	private String email;
	private long phone;
	private int age;
	private String gender;
	private String password;

}
