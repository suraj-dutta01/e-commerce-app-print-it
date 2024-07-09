package com.printit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.UserDao;
import com.printit.dto.EmailConfiguration;
import com.printit.dto.ResponseStructure;
import com.printit.dto.UserRequest;
import com.printit.dto.UserResponse;
import com.printit.exceptions.UserNotFoundException;
import com.printit.model.User;
import com.printit.util.AccountStatus;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailConfiguration emailConfiguration;
	@Autowired
	private LinkGenerationService linkGenerationService;
	@Autowired
	private PrintitApiMailSenderService mailSenderService;
	
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest,HttpServletRequest request){
		ResponseStructure<UserResponse> structure=new ResponseStructure<>();
		User user=maoToUser(userRequest);
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		String activation_link=linkGenerationService.getUserActivationLink(user, request);
		emailConfiguration.setToAddress(user.getEmail());
		emailConfiguration.setSubject("ACCOUNT ACTIVATION LINK");
		emailConfiguration.setText("Dear User, Please activate your account by clicking this link : "+activation_link);
		structure.setMessage(mailSenderService.sendMail(emailConfiguration));
		structure.setData(mapToUserResponse(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest,long id){
		ResponseStructure<UserResponse> structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.findById(id);
		if(resUser.isPresent()) {
			User dbUser=resUser.get();
			dbUser.setName(userRequest.getName());
			dbUser.setPhone(userRequest.getPhone());
			dbUser.setEmail(userRequest.getEmail());
			dbUser.setAge(userRequest.getAge());
			dbUser.setGender(userRequest.getGender());
			dbUser.setPassword(userRequest.getPassword());
			structure.setMessage("User Updated");
			structure.setData(mapToUserResponse(userDao.saveUser(dbUser)));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new UserNotFoundException("User id is invalid");
	}
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(String email,String password){
		ResponseStructure<UserResponse>structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.verifyUser(email, password);
		if(resUser.isPresent()) {
			structure.setMessage("User Verification Successfull");
			structure.setData(mapToUserResponse(resUser.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid email and password");
	}
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(long phone,String password){
		ResponseStructure<UserResponse>structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.verifyUser(phone, password);
		if(resUser.isPresent()) {
			structure.setMessage("User Verification Successfull");
			structure.setData(mapToUserResponse(resUser.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid phone and password");
	}
	public ResponseEntity<ResponseStructure<UserResponse>> findById(long id){
		ResponseStructure<UserResponse>structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.findById(id);
		if(resUser.isPresent()) {
			structure.setMessage("User Found");
			structure.setData(mapToUserResponse(resUser.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid User id");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteUser(long id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<User> resUser=userDao.findById(id);
		if(resUser.isPresent()) {
			User user=resUser.get();
			userDao.deleteUser(user.getId());
			structure.setData("User Deleted");
			structure.setMessage("User id found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("User id is Invalid");
	}
	
	public String activateAccount(String token) {
		Optional<User> resUser=userDao.findByToken(token);
		if(resUser.isPresent()) {
			User dbUser=resUser.get();
			dbUser.setStatus(AccountStatus.ACTIVE.toString());
			dbUser.setToken(null);
			userDao.saveUser(dbUser);
			return "Your Account has been Acctivated";
		}
		throw new UserNotFoundException("Invalid Tocken");
	}
	private User maoToUser(UserRequest userRequest) {
		return User.builder().name(userRequest.getName()).phone(userRequest.getPhone()).email(userRequest.getEmail())
				.age(userRequest.getAge()).gender(userRequest.getGender()).password(userRequest.getPassword()).build();
	}
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().id(user.getId()).name(user.getName()).phone(user.getPhone()).email(user.getEmail())
				.age(user.getAge()).gender(user.getGender()).password(user.getPassword()).build();
	}

}
