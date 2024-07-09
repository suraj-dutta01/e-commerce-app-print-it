package com.printit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.printit.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findUserByEmailAndPassword(String email,String password);
	Optional<User> findUserByPhoneAndPassword(long phone,String password);
	Optional<User> findByToken(String token);
	@Query("select a.token from User a where a.id=?1")
	String findTokenById(long id);
}
