package com.printit.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.Order;
import com.printit.repository.OrderRepository;

@Repository
public class OrderDao {
	@Autowired
	private OrderRepository orderRepository;
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	public Optional<Order> findById(long id){
		return orderRepository.findById(id);
	}

}
