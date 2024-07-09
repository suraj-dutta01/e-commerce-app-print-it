package com.printit.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.Address;
import com.printit.repository.AddressRepository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	public List<Address> findByUserId(long userId){
		return addressRepository.findByUserId(userId);
	}
	public Optional<Address> findById(long id){
		return addressRepository.findById(id);
	}
	public List<Address> findByCity(String city){
		return addressRepository.findByCity(city);
	}
	public List<Address> findByPinCode(int pinCode){
		return addressRepository.findByPinCode(pinCode);
	}
	public void deleteAddress(long id) {
		addressRepository.deleteById(id);
	}
	

}
