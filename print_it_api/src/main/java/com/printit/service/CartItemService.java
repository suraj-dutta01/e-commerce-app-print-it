package com.printit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.CartDao;
import com.printit.dao.CartItemDao;
import com.printit.dao.ProductDao;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.CartItemNotFoundException;
import com.printit.exceptions.CartNotFoundException;
import com.printit.exceptions.ProductNotFoundException;
import com.printit.model.Cart;
import com.printit.model.CartItem;
import com.printit.model.Product;

import jakarta.transaction.Transactional;

@Service
public class CartItemService {
	@Autowired
	private CartItemDao cartItemDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartDao cartDao;
	
	public ResponseEntity<ResponseStructure<CartItem>> saveCartIItem(long cartId,long productId,int quantity){
		ResponseStructure<CartItem> structure=new ResponseStructure<>();
		Optional<Cart> resCart=cartDao.findById(cartId);
		Optional<Product> resProduct=productDao.findById(productId);
		if(resCart.isPresent() && resProduct.isPresent()) {
			CartItem cartItem=CartItem.builder()
					          .cart(resCart.get())
					          .product(resProduct.get())
					          .quantity(quantity)
					          .price((resProduct.get().getPrice())*quantity).build();
			structure.setData(cartItemDao.saveCartItem(cartItem));
			structure.setMessage("Cart Item Saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new CartNotFoundException("Cart  id is Invalid");
	}
	public ResponseEntity<ResponseStructure<String>> deleteCartItem(long id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<CartItem> resCartItem=cartItemDao.findById(id);
		if(resCartItem.isPresent()) {
			System.err.println("vvvvvvvvvvvvvvvvvvvvvvvvvv");
			cartItemDao.deleteCartItem(id);
			structure.setData("Cart item Is Deleted");
			structure.setMessage("Cart Item Is Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new CartItemNotFoundException("CArt item Id is Invalid");
	}
	
	@Transactional
	public void addOrUpdateCartItem(Cart cart, long productId, int quantity) {
	    Optional<CartItem> existingItem = cart.getCartItems().stream()
	            .filter(item -> item.getProduct().getId() == productId)
	            .findFirst();

	    if (existingItem.isPresent()) {
	        // Update existing item quantity
	        CartItem cartItem = existingItem.get();
	        cartItem.setQuantity(cartItem.getQuantity() + quantity);
	        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
	        // No need to save explicitly here
	    } else {
	        // Add new item to the cart
	        Optional<Product> product = productDao.findById(productId);
	        if (product.isPresent()) {
	            CartItem newCartItem = CartItem.builder()
	                    .cart(cart)
	                    .product(product.get())
	                    .quantity(quantity)
	                    .price(product.get().getPrice() * quantity)
	                    .build();
	            cart.getCartItems().add(newCartItem);
	            // No need to save explicitly here
	        } else {
	            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
	        }
	    }
	}


}
