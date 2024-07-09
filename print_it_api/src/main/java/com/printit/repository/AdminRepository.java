package com.printit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.printit.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<Admin> findAdminByEmailAndPassword(String email,String password);
	Optional<Admin> findAdminByPhoneAndPassword(long phone,String password);
	Optional<Admin> findByToken(String token);
	@Query("select a.token from Admin a where a.id=?1")
	String findTokenById(long id);

}
