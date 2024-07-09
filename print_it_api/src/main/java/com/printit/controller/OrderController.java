package com.printit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.printit.dto.OrderResponse;
import com.printit.dto.ResponseStructure;
import com.printit.service.OrderService;

@RestController
@RequestMapping("api/orders")
@CrossOrigin
public class OrderController {
	@Autowired
	private OrderService orderService;
	@PostMapping("/{userId}/{cartId}/{paymentId}/{addressId}")
	public ResponseEntity<ResponseStructure<OrderResponse>> placeOrder(@PathVariable(name = "userId") long userId,@PathVariable(name = "cartId") long cartId,@PathVariable(name = "paymentId") long paymentId,@PathVariable(name = "addressId")long addressId){
		return orderService.placeOrder(userId, cartId, paymentId, addressId);
	}


}
