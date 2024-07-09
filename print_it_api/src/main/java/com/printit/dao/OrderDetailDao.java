package com.printit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.OrderDetail;
import com.printit.repository.OrderDetailRepository;

@Repository
public class OrderDetailDao {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}

}
