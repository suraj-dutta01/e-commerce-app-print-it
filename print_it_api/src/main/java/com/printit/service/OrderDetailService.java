package com.printit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.OrderDao;
import com.printit.dao.OrderDetailDao;
import com.printit.dao.ProductDao;
import com.printit.dto.OrderDetailResponse;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.OrderNotFoundException;
import com.printit.model.Order;
import com.printit.model.OrderDetail;
import com.printit.model.Product;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailDao orderDetailDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;
	
	public OrderDetail saveOrderDetails(long orderId,long productId,int quantity){
		Optional<Order> resOrder=orderDao.findById(orderId);
		Optional<Product> resProduct=productDao.findById(productId);
		ResponseStructure<OrderDetailResponse> structure=new ResponseStructure<>();
		if(resOrder.isPresent() && resProduct.isPresent()) {
			OrderDetail orderDetail=new OrderDetail();
			Order order=resOrder.get();
			Product product=resProduct.get();
			orderDetail.setProduct(product);
			orderDetail.setPrice(product.getPrice());
			orderDetail.setQuantity(quantity);
			orderDetail.setOrder(order);
			return orderDetailDao.saveOrderDetail(orderDetail);
		}
		throw new OrderNotFoundException("Order Id is Invalid");
	}
	

}
