package com.printit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.printit.dto.ResponseStructure;
import com.printit.model.Cart;
import com.printit.service.CardService;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin
public class CartController {
	@Autowired
	private CardService cartService;
	
	@PostMapping("/{userId}")
	public ResponseEntity<ResponseStructure<Cart>> saveCart(@PathVariable(name = "userId") long userId){
        return cartService.saveCart(userId);
	}
	@PostMapping("/add/{cartId}/{productId}/{quantity}")
	public ResponseEntity<ResponseStructure<Cart>> addToCart(@PathVariable(name = "cartId")long cartId, @PathVariable(name = "productId") long productId,@PathVariable(name = "quantity") int quantity){
		return cartService.addToCart(cartId, productId, quantity);
	}
	@DeleteMapping("/remove/{cartId}/{cartItemId}")
	public ResponseEntity<ResponseStructure<String>> removeFromCart(@PathVariable(name = "cartId")long cartId,@PathVariable(name = "cartItemId")long cartItemId){
		return cartService.removeFromCart(cartId, cartItemId);
	}
	@DeleteMapping("/{id}/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteCart(@PathVariable(name = "id")long id,@PathVariable(name = "userId")long userId){
		return cartService.deleteCart(id,userId);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<ResponseStructure<Cart>> findByUserId(@PathVariable(name = "userId")long userId){
		return cartService.findByUserId(userId);
	}
}
