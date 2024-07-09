package com.printit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CatagoryRequest {
	@NotBlank(message = "Name is Mendatory")
	private String name;

}
