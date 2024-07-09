package com.printit.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.Catagory;
import com.printit.repository.CatagoryRepository;

@Repository
public class CatagoryDao {
    @Autowired
	private CatagoryRepository catagoryRepository;
    
    public Catagory saveCatagory(Catagory catagory) {
    	return catagoryRepository.save(catagory);
    }
    public Optional<Catagory> findById(long id){
    	return catagoryRepository.findById(id);
    }
    public Optional<Catagory> findByName(String name){
    	return catagoryRepository.findByName(name);			
    }
    public void deleteCatagory(long id) {
    	catagoryRepository.deleteById(id);
    }
}
