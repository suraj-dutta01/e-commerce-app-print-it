package com.printit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.printit.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
