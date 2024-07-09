package com.printit.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.printit.model.CartItem;
import com.printit.repository.CartItemRepository;

@Repository
public class CartItemDao {
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public CartItem saveCartItem(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	public void deleteCartItem(long id) {
		cartItemRepository.deleteById(id);
	}
	public Optional<CartItem> findById(long id){
		return cartItemRepository.findById(id);
	}

}
