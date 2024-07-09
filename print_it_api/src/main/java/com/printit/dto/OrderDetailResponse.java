package com.printit.dto;

import com.printit.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
	private long id;
	private Product product;
	private int quantity;
    private double price;
    private double totalPrice;

}
