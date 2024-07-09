package com.printit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.printit.model.Catagory;

public interface CatagoryRepository extends JpaRepository<Catagory, Long>{

	Optional<Catagory> findByName(String name);
	
}
