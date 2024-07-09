package com.printit.controller;

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

import com.printit.dto.CatagoryRequest;
import com.printit.dto.CatagoryResponse;
import com.printit.dto.ResponseStructure;
import com.printit.service.CatagoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catagorys")
@CrossOrigin
public class CatagoryController {
	@Autowired
	private CatagoryService catagoryService;
	@PostMapping
	public ResponseEntity<ResponseStructure<CatagoryResponse>> saveCatagory(@Valid @RequestBody CatagoryRequest catagoryRequest){
		return catagoryService.saveCatagory(catagoryRequest);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<CatagoryResponse>> updateCatagory(@Valid @RequestBody CatagoryRequest catagoryRequest,@PathVariable(name = "id") long id){
		return catagoryService.updateCatagory(catagoryRequest, id);
	}
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStructure<CatagoryResponse>> findByid(@PathVariable(name = "id") long id){
		return catagoryService.findByid(id);
	}
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<CatagoryResponse>> findByName(@PathVariable(name = "name") String name){
		return catagoryService.findByName(name);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCatagory(@PathVariable(name = "id") long id) {
		return catagoryService.deleteCatagory(id);
	}

}
