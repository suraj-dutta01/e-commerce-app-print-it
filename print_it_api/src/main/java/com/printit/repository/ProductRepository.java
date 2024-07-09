package com.printit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.printit.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("select p from Product p where p.name=?1")
	List<Product> findByName(String name);
	@Query("select p from Product p where p.price>=?1 and p.price<=?2")
	List<Product> findByPrineRange(double startPrice,double endPrice);
	@Query("select p from Product p where p.catagory.name=?1")
	List<Product> findByCatagoryName(String name);

}
