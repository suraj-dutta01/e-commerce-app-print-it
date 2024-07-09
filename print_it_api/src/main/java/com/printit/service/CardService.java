package com.printit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.CartDao;
import com.printit.dao.UserDao;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.CartItemNotFoundException;
import com.printit.exceptions.ProductNotFoundException;
import com.printit.exceptions.UserNotFoundException;
import com.printit.model.Cart;
import com.printit.model.User;

@Service
public class CardService {
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CartItemService cartItemService;
	
	public ResponseEntity<ResponseStructure<Cart>> saveCart(long userId){
		ResponseStructure<Cart> structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.findById(userId);
		if(resUser.isPresent()) {
			System.err.println("user present");
			User user=resUser.get();
			System.err.println("get also");
			Cart cart=Cart.builder().user(user).build();
			System.err.println("Problem is there");
			user.setCart(cart);		
			structure.setMessage("Cart Save");
			structure.setData(cartDao.saveCart(cart));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new UserNotFoundException("User Id is Invalid");	
	}
	public ResponseEntity<ResponseStructure<Cart>> addToCart(long cartId,long productId,int quantity){
		ResponseStructure<Cart>structure =new ResponseStructure<>();
		Optional<Cart> resCart=cartDao.findById(cartId);
		if(resCart.isPresent()) {
			Cart cart=resCart.get();
			 try {
		            cartItemService.addOrUpdateCartItem(cart, productId, quantity);
		        } catch (ProductNotFoundException e) {
		            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
		        }
			 cartDao.saveCart(cart);
			    structure.setMessage("Item added to Cart");
		        structure.setData(cart);
		        structure.setStatusCode(HttpStatus.OK.value());
		        return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new CartItemNotFoundException("Cart Id is Invalid");
	}
	public ResponseEntity<ResponseStructure<String>> removeFromCart(long cartId,long cartItemId){
		ResponseStructure<String>structure=new ResponseStructure<>();
		Optional<Cart>resCart=cartDao.findById(cartId);
		if(resCart.isPresent()) {
			System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
			cartItemService.deleteCartItem(cartItemId);
			structure.setData("CArt Item Is Deleted");
			structure.setMessage("Cart Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new CartItemNotFoundException("Cart Id is Invalid");
	}
	public ResponseEntity<ResponseStructure<String>> deleteCart(long id,long userId){
		ResponseStructure<String>structure=new ResponseStructure<>();
		Optional<Cart>resCart=cartDao.findById(id);
		Optional<User> resUser=userDao.findById(userId);
		if(resCart.isPresent()) {
			User user=resUser.get();
			user.setCart(null);
			Cart cart=resCart.get();
			cart.setUser(null);
			cartDao.deleteCart(id);
			
			structure.setData("Cart deleted");
			structure.setMessage("Cart Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new CartItemNotFoundException("Cart Id is Invalid");
	}
	public ResponseEntity<ResponseStructure<Cart>> findByUserId(long userId){
		ResponseStructure<Cart>structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.findById(userId);
		if(resUser.isPresent()){
			Optional<Cart> cartRes=cartDao.findByUserId(userId);
			if(cartRes.isPresent()) {
				Cart cart=cartRes.get();
				structure.setData(cart);
				structure.setMessage("Cart Found");
				structure.setStatusCode(HttpStatus.OK.value());
				return ResponseEntity.status(HttpStatus.OK).body(structure);
			}
		}
		throw new UserNotFoundException("User Id is Invalid");
	}
	
	

}
