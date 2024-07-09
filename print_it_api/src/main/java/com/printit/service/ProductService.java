package com.printit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.CatagoryDao;
import com.printit.dao.ProductDao;
import com.printit.dto.ProductRequest;
import com.printit.dto.ProductResponse;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.CatagoryNotFoundException;
import com.printit.exceptions.ProductNotFoundException;
import com.printit.model.Catagory;
import com.printit.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CatagoryDao catagoryDao;
	
	public ResponseEntity<ResponseStructure<ProductResponse>> saveProduct(ProductRequest productRequest,long catagoryId){
		ResponseStructure<ProductResponse>structure=new ResponseStructure<>();
		Optional<Catagory> resCatagory=catagoryDao.findById(catagoryId);
		if(resCatagory.isPresent()) {
			Product product=mapToProduct(productRequest);
			Catagory catagory=resCatagory.get();
			catagory.getProducts().add(product);
//			catagoryDao.saveCatagory(catagory);
			product.setCatagory(catagory);
			structure.setMessage("Product saved");
			structure.setData(mapToProductResponse(productDao.saveProduct(product)));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new CatagoryNotFoundException("Catagory id is invalid");	
	}
	public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(ProductRequest productRequest,long id){
		ResponseStructure<ProductResponse> structure=new ResponseStructure<>();
		Optional<Product> resProduct=productDao.findById(id);
		if(resProduct.isPresent()) {
			Product dbProduct=resProduct.get();
			dbProduct.setName(productRequest.getName());
			dbProduct.setDescription(productRequest.getDescription());
			dbProduct.setPrice(productRequest.getPrice());
			dbProduct.getImages().addAll(productRequest.getImages());
			dbProduct.setStock(productRequest.getStock());
			
			structure.setMessage("Product Updated");
			structure.setData(mapToProductResponse(productDao.saveProduct(dbProduct)));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new ProductNotFoundException("Product Id is Invalid");
	}
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findProductsByName(String name){
		ResponseStructure<List<ProductResponse>> structure=new ResponseStructure<>();
		List<Product> resProducts=productDao.findByName(name);
		if(resProducts.size()>0) {
			List<ProductResponse> result=new ArrayList<>();
			for(Product l:resProducts) {
				if(l.getCatagory()!=null) {
					result.add(mapToProductResponse(l));
				}
			}
			structure.setMessage("Products Found With this name");
			structure.setData(result);
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Product Not Found with this name");
	}
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findProductsByCatagoryName(String catagoryNmae){
		ResponseStructure<List<ProductResponse>> structure=new ResponseStructure<>();
		List<Product> resProducts=productDao.findByCatagoryName(catagoryNmae);
		if(resProducts.size()>0) {
			List<ProductResponse> result=new ArrayList<>();
			for(Product l:resProducts) {
				result.add(mapToProductResponse(l));
			}
			structure.setMessage("Products Found in this catogory");
			structure.setData(result);
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Product Not Found in this catagory");
	}
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findProductsByPriceRange(double startPrice,double endPrice){
		ResponseStructure<List<ProductResponse>> structure=new ResponseStructure<>();
		List<Product> resProducts=productDao.findByPriceRange(startPrice, endPrice);
		if(resProducts.size()>0) {
			List<ProductResponse> result=new ArrayList<>();
			for(Product l:resProducts) {
				if(l.getCatagory()!=null) {
					result.add(mapToProductResponse(l));
				}
			}
			structure.setMessage("Products Found in this Range");
			structure.setData(result);
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Product Not Found in this Range");
	}
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findAllProduct(){
		ResponseStructure<List<ProductResponse>> structure=new ResponseStructure<>();
		List<Product> resProducts=productDao.findAllProduct();
		if(resProducts.size()>0) {
			List<ProductResponse> result=new ArrayList<>();
			for(Product l:resProducts) {
				if(l.getCatagory()!=null) {
					result.add(mapToProductResponse(l));
				}
			}
			structure.setMessage("Products Found");
			structure.setData(result);
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Product Not Found");
	}
	public ResponseEntity<ResponseStructure<String>> deleteProduct(long id) {
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Product> resProduct=productDao.findById(id);
		if(resProduct.isPresent()) {
			productDao.deleteProduct(id);
			structure.setData("Product Deleted");
			structure.setMessage("Product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Product Id is invalid");
	}
	
	private Product mapToProduct(ProductRequest productRequest) {
		return Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice())
				.images(productRequest.getImages()).stock(productRequest.getStock()).build();
	}
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice())
				.images(product.getImages()).stock(product.getStock()).catagoryName(product.getCatagory().getName()).build();
	}
}
