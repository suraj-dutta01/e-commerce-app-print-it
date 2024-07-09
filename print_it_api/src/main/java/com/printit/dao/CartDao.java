package com.printit.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.Cart;
import com.printit.repository.CartRepository;

@Repository
public class CartDao {
	@Autowired
	private CartRepository cartRepository;
	
	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}
	public Optional<Cart> findById(long id){
		return cartRepository.findById(id);
	}
	public Optional<Cart> findByUserId(long userId){
		return cartRepository.findCartByUserId(userId);
	}
	public void deleteCart(long id) {
		cartRepository.deleteById(id);
	}
	

}
