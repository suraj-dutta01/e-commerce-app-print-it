package com.printit.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
	private long id;
	private String name;
	private String description;
	private double price;
    private List<String> images;
    private int stock;
    private String catagoryName;

}
