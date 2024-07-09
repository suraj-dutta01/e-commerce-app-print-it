package com.printit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.printit.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
