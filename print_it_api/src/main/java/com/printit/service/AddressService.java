package com.printit.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.AddressDao;
import com.printit.dao.UserDao;
import com.printit.dto.AddressRequest;
import com.printit.dto.AddressResponse;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.AddressNotFoundException;
import com.printit.exceptions.UserNotFoundException;
import com.printit.model.Address;
import com.printit.model.User;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<AddressResponse>> saveAddress(AddressRequest addressRequest,long userId){
		ResponseStructure<AddressResponse> structure=new ResponseStructure<>();
		Optional<User>resUser=userDao.findById(userId);
		if(resUser.isPresent()) {
			User user=resUser.get();
			Address address=mapToAddress(addressRequest);
			address.setUser(user);
			user.getAddresses().add(address);
			address=addressDao.saveAddress(address);
			user=userDao.saveUser(user);
			structure.setMessage("Address saved successfully");
			structure.setData(mapToAddressResponse(address));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new UserNotFoundException("User id is Invalid");
	}
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest,long id){
		ResponseStructure<AddressResponse> structure=new ResponseStructure<>();
		Optional<Address> resAddress=addressDao.findById(id);
		if(resAddress.isPresent()) {
			Address dbAddress=resAddress.get();
			dbAddress.setName(addressRequest.getName());
			dbAddress.setAddressLine1(addressRequest.getAddressLine1());
			dbAddress.setAddressLine2(addressRequest.getAddressLine2());
			dbAddress.setAddressType(addressRequest.getAddressType());
			dbAddress.setCity(addressRequest.getCity());
			dbAddress.setCountry(addressRequest.getCountry());
			dbAddress.setPhone(addressRequest.getPhone());
			dbAddress.setPinCode(addressRequest.getPinCode());
			dbAddress.setState(addressRequest.getState());
			structure.setMessage("Address Updated");
			structure.setData(mapToAddressResponse(addressDao.saveAddress(dbAddress)));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new AddressNotFoundException("Address id is invalid");
	}
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findByUserId(long userId){
		ResponseStructure<List<AddressResponse>>structure=new ResponseStructure<>();
		List<Address> addressList=addressDao.findByUserId(userId);
		if(userDao.findById(userId)!=null) {
			if(addressList.size()>0) {
				structure.setMessage("Address found");
				structure.setStatusCode(HttpStatus.OK.value());
				List<AddressResponse>ans=new ArrayList<>();
				for(Address add:addressList) {
					ans.add(mapToAddressResponse(add));
				}
				structure.setData(ans);
				return ResponseEntity.status(HttpStatus.OK).body(structure);
			}
			throw new AddressNotFoundException("Address not found in this user");
		}
		throw new UserNotFoundException("User id is invalid");
	}
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findByCity(String city){
		ResponseStructure<List<AddressResponse>>structure=new ResponseStructure<>();
		List<Address> addressList=addressDao.findByCity(city);
			if(addressList.size()>0) {
				structure.setMessage("Address found");
				structure.setStatusCode(HttpStatus.OK.value());
				List<AddressResponse>ans=new ArrayList<>();
				for(Address add:addressList) {
					ans.add(mapToAddressResponse(add));
				}
				structure.setData(ans);
				return ResponseEntity.status(HttpStatus.OK).body(structure);
			}
			throw new AddressNotFoundException("Address not found in this user");
	}
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findByPinCode(int pinCode){
		ResponseStructure<List<AddressResponse>>structure=new ResponseStructure<>();
		List<Address> addressList=addressDao.findByPinCode(pinCode);
			if(addressList.size()>0) {
				structure.setMessage("Address found");
				structure.setStatusCode(HttpStatus.OK.value());
				List<AddressResponse>ans=new ArrayList<>();
				for(Address add:addressList) {
					ans.add(mapToAddressResponse(add));
				}
				structure.setData(ans);
				return ResponseEntity.status(HttpStatus.OK).body(structure);
			}
			throw new AddressNotFoundException("Address not found in this user");
	}
	public ResponseEntity<ResponseStructure<String>> deleteAddress(long id){
		ResponseStructure<String>structure=new ResponseStructure<>();
		Optional<Address> resAddress=addressDao.findById(id);
		if(resAddress.isPresent()) {
			Address address=resAddress.get();
			address.setUser(null);
			//need to be check after creating order API's
			address.setOrders(null);
			addressDao.deleteAddress(id);
			structure.setData("Address deleted");
			structure.setMessage("Address found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AddressNotFoundException("Address id is invalid");
	}
	
	private Address mapToAddress(AddressRequest addressRequest) {
		return Address.builder().name(addressRequest.getName()).addressLine1(addressRequest.getAddressLine1()).addressLine2(addressRequest.getAddressLine2())
				.city(addressRequest.getCity()).state(addressRequest.getState()).pinCode(addressRequest.getPinCode()).country(addressRequest.getCountry())
				.phone(addressRequest.getPhone()).addressType(addressRequest.getAddressType()).build();
	}
	private AddressResponse mapToAddressResponse(Address address) {
		return AddressResponse.builder().id(address.getId()).name(address.getName()).addressLine1(address.getAddressLine1()).addressLine2(address.getAddressLine2())
				.city(address.getCity()).state(address.getState()).pinCode(address.getPinCode()).country(address.getCountry()).phone(address.getPhone()).addressType(address.getAddressType()).build();
	}
	

}
