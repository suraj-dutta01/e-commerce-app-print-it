package com.printit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.printit.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
