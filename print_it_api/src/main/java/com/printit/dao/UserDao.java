package com.printit.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.printit.model.User;
import com.printit.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	public Optional<User> findById(long id){
		return userRepository.findById(id);
	}
	public Optional<User> verifyUser(String email,String password){
		return userRepository.findUserByEmailAndPassword(email, password);
	}
	public Optional<User> verifyUser(long phone,String password){
		return userRepository.findUserByPhoneAndPassword(phone, password);
	}
	public Optional<User> findByToken(String token){
		return userRepository.findByToken(token);
	}
	public String findTokenById(long id) {
		return userRepository.findTokenById(id);
	}
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

}
