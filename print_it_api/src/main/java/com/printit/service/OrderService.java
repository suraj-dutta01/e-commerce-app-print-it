package com.printit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.AddressDao;
import com.printit.dao.CartDao;
import com.printit.dao.OrderDao;
import com.printit.dao.UserDao;
import com.printit.dto.EmailConfiguration;
import com.printit.dto.OrderDetailResponse;
import com.printit.dto.OrderResponse;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.OrderNotFoundException;
import com.printit.model.Address;
import com.printit.model.Cart;
import com.printit.model.CartItem;
import com.printit.model.Order;
import com.printit.model.OrderDetail;
import com.printit.model.User;
import com.printit.util.OrderStatus;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private EmailConfiguration emailConfiguration;
	@Autowired
	private PrintitApiMailSenderService mailSenderService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CardService cartService;
	
	public ResponseEntity<ResponseStructure<OrderResponse>> placeOrder(long userId,long cartId,long paymentId,long addressId){
		ResponseStructure<OrderResponse> structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.findById(userId);
		Optional<Cart> resCart=cartDao.findById(cartId);
		Optional<Address> resAddress=addressDao.findById(addressId);
//      Optional<Payment> resPayment= need to do future
		if(resUser.isPresent() && resCart.isPresent() && resAddress.isPresent()) {
			Order dbOrder=new Order();
			User user=resUser.get();
			Cart cart=resCart.get();
			Address address=resAddress.get();
			dbOrder.setAddress(address);
			dbOrder.setUser(user);
			dbOrder.setStatus(OrderStatus.PROCESSING.toString());
			List<CartItem> items=cart.getCartItems();
			double amount=0;			
			for(CartItem i:items) {
				amount=amount+(i.getPrice());
			}
			dbOrder.setTotalPrice(amount);
//          dbOrder.setPayments(null);
			dbOrder=orderDao.saveOrder(dbOrder);
			List<OrderDetail> details=new ArrayList<>();
			for(CartItem i:items) {
				OrderDetail response=orderDetailService.saveOrderDetails(dbOrder.getId(), i.getProduct().getId(), i.getQuantity());
                details.add(response);
			}
			dbOrder.setOrderDetails(details);
			
			
			emailConfiguration.setToAddress(user.getEmail());
			emailConfiguration.setSubject("YOUR ORDER IS PLACED");
			emailConfiguration.setText("Dear, User Your order is placed. Thank you for Purchaising from us");
			structure.setMessage(mailSenderService.sendMail(emailConfiguration));
			structure.setData(mapToOrderResponse(dbOrder));
			structure.setStatusCode(HttpStatus.CREATED.value());

			cartService.deleteCart(cartId, userId);
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new OrderNotFoundException("It is For Testing");
		
		
	}
    private OrderResponse mapToOrderResponse(Order order) {
    	return OrderResponse.builder().id(order.getId()).userName(order.getUser().getName()).status(order.getStatus())
    			.totalPrice(order.getTotalPrice()).orderDateAndTime(order.getOrderDateAndTime()).deliveryDate(order.getDeliveryDate())
    			.address(order.getAddress()).orderDetails(order.getOrderDetails()).build();
    	        //.paymentStatus(order.getPayments().get(0).getStatus())
    }
//    private OrderDetailResponse mapToOrderDetailsResponse(OrderDetail orderDetail) {
//		return OrderDetailResponse.builder().id(orderDetail.getId()).product(orderDetail.getProduct()).price(orderDetail.getPrice())
//				.totalPrice((orderDetail.getPrice())*orderDetail.getQuantity()).quantity(orderDetail.getQuantity()).build();
//	}

}
