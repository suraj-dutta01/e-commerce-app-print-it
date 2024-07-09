package com.printit.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class ProductRequest {
	@NotBlank(message = "Name is Mendatory")
	private String name;
	@NotBlank(message = "Please Provide the Description")
	private String description;
	private double price;
    private List<String> images;
    private int stock;

}
