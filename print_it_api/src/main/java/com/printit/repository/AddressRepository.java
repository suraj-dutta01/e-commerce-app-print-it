package com.printit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.printit.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	List<Address> findByUserId(long id);
	List<Address> findByCity(String city);
	List<Address> findByPinCode(int pinCode);
	

}
