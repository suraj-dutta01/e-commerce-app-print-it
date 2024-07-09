package com.printit.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.CatagoryDao;
import com.printit.dto.CatagoryRequest;
import com.printit.dto.CatagoryResponse;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.CatagoryNotFoundException;
import com.printit.model.Catagory;

@Service
public class CatagoryService {
	@Autowired
	private CatagoryDao catagoryDao;
	
	
	public ResponseEntity<ResponseStructure<CatagoryResponse>> saveCatagory(CatagoryRequest catagoryRequest){
		ResponseStructure<CatagoryResponse>structure=new ResponseStructure<>();
		Catagory catagory=mapToCatagory(catagoryRequest);
		structure.setMessage("Catagory saved");
		structure.setData(mapToCatagoryResponse(catagoryDao.saveCatagory(catagory)));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	public ResponseEntity<ResponseStructure<CatagoryResponse>> updateCatagory(CatagoryRequest catagoryRequest,long id){
		ResponseStructure<CatagoryResponse>structure=new ResponseStructure<>();
        Optional<Catagory>resCatagory=catagoryDao.findById(id);
        if(resCatagory.isPresent()) {
        	Catagory dbCatagory=resCatagory.get();
        	dbCatagory.setName(catagoryRequest.getName());
        	structure.setMessage("Catagory updated");
        	structure.setData(mapToCatagoryResponse(catagoryDao.saveCatagory(dbCatagory)));
        	structure.setStatusCode(HttpStatus.ACCEPTED.value());
        	return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
        }
       throw new CatagoryNotFoundException("Catagory id is invalid");
	}
	public ResponseEntity<ResponseStructure<CatagoryResponse>> findByid(long id){
		ResponseStructure<CatagoryResponse>structure=new ResponseStructure<>();
        Optional<Catagory>resCatagory=catagoryDao.findById(id);
        if(resCatagory.isPresent()) {
        	structure.setData(mapToCatagoryResponse(resCatagory.get()));
        	structure.setMessage("Catagory found");
        	structure.setStatusCode(HttpStatus.OK.value());
        	return ResponseEntity.status(HttpStatus.OK).body(structure);
        }
        throw new CatagoryNotFoundException("Catagory id is invalid");
	}
	public ResponseEntity<ResponseStructure<CatagoryResponse>> findByName(String name){
		ResponseStructure<CatagoryResponse>structure=new ResponseStructure<>();
        Optional<Catagory>resCatagory=catagoryDao.findByName(name);
        if(resCatagory.isPresent()) {
        	structure.setData(mapToCatagoryResponse(resCatagory.get()));
        	structure.setMessage("Catagory found");
        	structure.setStatusCode(HttpStatus.OK.value());
        	return ResponseEntity.status(HttpStatus.OK).body(structure);
        }
        throw new CatagoryNotFoundException("Catagory id is invalid");
	}
	public ResponseEntity<ResponseStructure<String>> deleteCatagory(long id) {
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Catagory> resCatagory=catagoryDao.findById(id);
		if(resCatagory.isPresent()) {
			catagoryDao.deleteCatagory(id);
			structure.setData("Catagory Deleted");
			structure.setMessage("Catagory found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new CatagoryNotFoundException("Catagory id is invalid");
	}
	
	
	
	
	private Catagory mapToCatagory(CatagoryRequest request) {
		return Catagory.builder().name(request.getName()).build();
	}
	private CatagoryResponse mapToCatagoryResponse(Catagory catagory) {
		return CatagoryResponse.builder().id(catagory.getId()).name(catagory.getName()).build();
	}
	
	

}
