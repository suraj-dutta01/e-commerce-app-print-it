package com.printit.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.Product;
import com.printit.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	public List<Product> findByName(String name){
		return productRepository.findByName(name);
	}
	public Optional<Product> findById(long id){
		return productRepository.findById(id);
	}
	public List<Product> findByCatagoryName(String name){
		return productRepository.findByCatagoryName(name);
	}
	public List<Product> findByPriceRange(double startPrice,double endPrice){
		return productRepository.findByPrineRange(startPrice, endPrice);
	}
	public List<Product> findAllProduct(){
		return productRepository.findAll();
	}
    public void deleteProduct(long id) {
    	productRepository.deleteById(id);
    }
}
