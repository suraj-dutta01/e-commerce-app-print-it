package com.printit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.printit.dto.ProductRequest;
import com.printit.dto.ProductResponse;
import com.printit.dto.ResponseStructure;
import com.printit.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService productService;
    
	@PostMapping("/{catagoryId}")
	public ResponseEntity<ResponseStructure<ProductResponse>> saveProduct(@Valid @RequestBody ProductRequest productRequest,@PathVariable(name = "catagoryId") long catagoryId){
           return productService.saveProduct(productRequest, catagoryId);	
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(@Valid @RequestBody ProductRequest productRequest,@PathVariable(name = "id") long id){
		   return productService.updateProduct(productRequest, id);
	}
	@GetMapping("/{name}")
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findProductsByName(@PathVariable(name = "name") String name){
		return productService.findProductsByName(name);
	}
	@GetMapping("/catagory/{catagoryNmae}")
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findProductsByCatagoryName(@PathVariable(name = "catagoryNmae") String catagoryNmae){
		return productService.findProductsByCatagoryName(catagoryNmae);
	}
	@GetMapping("/{startPrice}/{endPrice}")
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findProductsByPriceRange(@PathVariable(name = "startPrice") double startPrice,@PathVariable(name = "endPrice") double endPrice){
		return productService.findProductsByPriceRange(startPrice, endPrice);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findAllProduct(){
		return productService.findAllProduct();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable(name = "id") long id) {
		return productService.deleteProduct(id);
	}
}
