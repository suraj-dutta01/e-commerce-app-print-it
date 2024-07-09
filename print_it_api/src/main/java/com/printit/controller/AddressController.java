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

import com.printit.dto.AddressRequest;
import com.printit.dto.AddressResponse;
import com.printit.dto.ResponseStructure;
import com.printit.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin
public class AddressController {
	@Autowired
	private AddressService addressService;
	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<AddressResponse>> saveAddress(@Valid @RequestBody AddressRequest addressRequest,@PathVariable(value = "id") long userId){
	     return addressService.saveAddress(addressRequest, userId);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid @RequestBody AddressRequest addressRequest,@PathVariable(value = "id") long id){
		return addressService.updateAddress(addressRequest, id);
	}
	@GetMapping("/uid/{userId}")
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findByUserId(@PathVariable(value = "userId") long userId){
		return addressService.findByUserId(userId);
	}
	@GetMapping("/city/{city}")
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findByCity(@PathVariable(value = "city") String city){
		return addressService.findByCity(city);
	}
	@GetMapping("/pincode/{pinCode}")
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findByPinCode(@PathVariable(value = "pinCode") int pinCode){
		return addressService.findByPinCode(pinCode);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAddress(@PathVariable(value = "id") long id){
		return addressService.deleteAddress(id);
	}

}
