package com.printit.dao;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.Admin;
import com.printit.repository.AdminRepository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	public Optional<Admin> findById(long id){
		return adminRepository.findById(id);
	}
	public Optional<Admin> verifyAdmin(String email,String password){
		return adminRepository.findAdminByEmailAndPassword(email, password);
	}
	public Optional<Admin> verifyAdmin(long phone,String password){
		return adminRepository.findAdminByPhoneAndPassword(phone, password);
	}
	public Optional<Admin> findByToken(String token){
		return adminRepository.findByToken(token);
	}
	public String findTokenById(long id) {
		return adminRepository.findTokenById(id);
	}
	public void deleteAdmin(long id) {
		adminRepository.deleteById(id);
	}
	

}
