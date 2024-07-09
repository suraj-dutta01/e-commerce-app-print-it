package com.printit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.printit.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("select c from Cart c where c.user.id=?1")
	Optional<Cart> findCartByUserId(long userId);

}
